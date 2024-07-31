package dev.bengi.authservice.service.Impl;

import dev.bengi.authservice.dto.AuthenticatedUser;
import dev.bengi.authservice.dto.LoginResponseDTO;
import dev.bengi.authservice.exception.RefreshTokenExpiredException;
import dev.bengi.authservice.model.RefreshToken;
import dev.bengi.authservice.model.UserLogin;
import dev.bengi.authservice.dto.LogoutDTO;
import dev.bengi.authservice.repository.RefreshTokenRepository;
import dev.bengi.authservice.repository.UserLoginRepository;
import dev.bengi.authservice.service.AuthService;
import dev.bengi.authservice.dto.RefreshTokenDTO;
import dev.bengi.userservice.repository.UserRepository;
import dev.bengi.userservice.enumeration.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.bengi.userservice.model.User;
import org.springframework.transaction.annotation.Transactional;
import dev.bengi.authservice.service.TokenService;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Optional;

/**
 * Service for handling authentication operations.
 * @author bengi
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    public static final String TOKEN_TYPE = "bearer";

    // Constants
    private final PasswordEncoder passwordEncoder;
    private final UserLoginRepository userLoginRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    /**
     * Constructor for the AuthServiceImpl class.
     * @param passwordEncoder The password encoder to use.
     * @param userLoginRepository The repository for user logins.
     * @param authenticationManager The authentication manager.
     *                                This is used to authenticate users.
     * @param refreshTokenRepository The repository for refresh tokens.
     * @param tokenService The token service.
     * @param userRepository The repository for users.
     */
    // Dependencies
    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserLoginRepository userLoginRepository, AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, TokenService tokenService, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userLoginRepository = userLoginRepository;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user login by email.
     * @param email The email of the user login.
     * @return The user login with the provided email, if it exists.
     */
    @Override
    public Optional<UserLogin> findCredentialByEmail(String email) {
        return userLoginRepository.findOneByEmail(email);
    }

    /**
     * Creates a new user login for a customer.
     * @param userId The ID of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The created user login.
     */
    @Override
    public UserLogin createConsumerCredentials(int userId, String email, String password) {
        // Map the user ID to a UserLogin aggregate reference
        AggregateReference<User, Integer> userAggregateReference = AggregateReference.to(userId);

        // Encrypt the password using the password encoder
        var encryptedPassword = passwordEncoder.encode(password);

        // Create a new UserLogin object with the provided information
        var userCredential = new UserLogin(null, userAggregateReference, email, encryptedPassword);

        // Save the user login to the repository and log the creation
        var createdCredential = userLoginRepository.save(userCredential);
        logger.info("Created credential for user: {}", userId);

        // Return the created user login
        return createdCredential;
    }

    /**
     * Retrieves a user login by user ID.
     * @param userId The ID of the user.
     * @return The user login with the provided user ID, if it exists.
     */
    @Override
    public Optional<UserLogin> findCredentialByUserId(int userId) {
        // Map the user ID to a UserLogin aggregate reference
        AggregateReference<User, Integer> userAggregateReference = AggregateReference.to(userId);
        return userLoginRepository.findOneByUserId(userAggregateReference);
    }

    /**
     * Deletes a user login by user ID.
     * @param userId The ID of the user.
     */
    @Override
    public void deleteCredentialByUserId(int userId) {
        var credential = findCredentialByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Credential for %s not found", userId)));
        userLoginRepository.delete(credential);
    }

    /**
     * Authenticates a user and issues an access token and refresh token.
     * Logs out the user if they are already logged in.
     * Saves the new refresh token to the database.
     * @param body The login request body containing email and password.
     * @return The login response containing user ID, token type, access token, and refresh token.
     */
    @Override
    @Transactional
    public LoginResponseDTO login(LoginResponseDTO body) {
        // Create authentication token with email and password
        var authInfo = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        // Authenticate the user
        var authentication = authenticationManager.authenticate(authInfo);
        // Get the authenticated user
        var authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

        // Get the current time
        var now = Instant.now();
        // Issue an access token
        var accessToken = tokenService.issueAccessToken(authentication, now);
        // Issue a refresh token
        var refreshToken = tokenService.issueRefreshToken();

        // Log out the user if they are already logged in
        logout(authentication);

        // Save new refresh token
        var prepareRefreshTokenModel = new RefreshToken(
                null,
                refreshToken,
                now,
                authenticatedUser.role().name(),
                authenticatedUser.userId(),
                false);
        refreshTokenRepository.save(prepareRefreshTokenModel);

        // Return the login response
        return new LoginResponseDTO(
                authenticatedUser.userId(),
                TOKEN_TYPE,
                accessToken,
                refreshToken);
    }

    /**
     * This method issues a new access token using a refresh token.
     * It first checks if the refresh token exists and is not expired.
     * If the refresh token is expired, it logs out the user and throws a RefreshTokenExpiredException.
     * If the refresh token is not expired, it generates a new access token and rotates the refresh token if necessary.
     *
     * @param body The refresh token DTO containing the refresh token
     * @return The login response DTO containing the new access token, refresh token, and user ID
     * @throws EntityNotFoundException If the refresh token is not found
     * @throws RefreshTokenExpiredException If the refresh token is expired
     */
    @Override
    @Transactional
    public LoginResponseDTO issueNewAccessToken(RefreshTokenDTO body) {
        // Check refresh token is existing?
        var refreshTokenEntity = refreshTokenRepository.findOneByToken(body.refreshToken())
                .orElseThrow(() -> new EntityNotFoundException("This refresh token not found"));
        var resourceId = refreshTokenEntity.resourceId();
        // Expired? - DB -> IssuedDate + configured expire time
        if (tokenService.isRefreshTokenExpired(refreshTokenEntity)) {
            logout(new LogoutDTO(String.valueOf(resourceId), refreshTokenEntity.usage()));
            // Need re-login
            throw new RefreshTokenExpiredException("This refresh token is expired");
        }
        /**
         * Waiting for Store - Service Completed.
         */
//        String newAccessToken = switch (RoleEnum.valueOf(body.usage())) {
//            case RoleEnum.STORE -> {
//                AggregateReference<Store, Integer> StoreReference = AggregateReference.to(resourceId);
//                var credential = StoreLoginRepository.findOneByTourCompanyId(StoreReference)
//                        .orElseThrow(() -> new EntityNotFoundException(
//                                String.format("Store Id: %d not found", resourceId)));
//                yield tokenService.issueAccessToken((Authentication) credential, Instant.now());
//            }
        // Token almost expired => refresh token rotation
        String newAccessToken = switch (RoleEnum.valueOf(body.usage())) {
            default -> {
                var user = userRepository.findById(resourceId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                String.format("User Id: %d not found", resourceId)));
                var credential = findCredentialByUserId(user.id())
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        String.format("Credential for user Id: %d not found",
                                                user.id())));
                yield tokenService.issueAccessToken(credential, Instant.now());
            }
        };
        var refreshToken = tokenService.rotateRefreshTokenIfNeed(refreshTokenEntity);
        // Check if refresh token change -> change old refresh token to expired
        if (!refreshToken.equals(refreshTokenEntity.token())) {
            var updatedRefreshTokenEntity = new RefreshToken(
                    refreshTokenEntity.id(),
                    refreshTokenEntity.token(),
                    refreshTokenEntity.issuedAt(),
                    refreshTokenEntity.usage(),
                    refreshTokenEntity.resourceId(),
                    true);
            refreshTokenRepository.save(updatedRefreshTokenEntity);
            var prepareRefreshTokenModel = new RefreshToken(
                    null,
                    refreshToken,
                    Instant.now(),
                    refreshTokenEntity.usage(),
                    refreshTokenEntity.resourceId(),
                    false);
            refreshTokenRepository.save(prepareRefreshTokenModel);
        }
        return new LoginResponseDTO(
                refreshTokenEntity.resourceId(),
                TOKEN_TYPE,
                newAccessToken,
                refreshToken);
    }

    /**
     * Logs out the user by updating their refresh token to indicate they are logged out.
     * @param authentication The authentication object containing the user's information.
     */
    @Override
    public void logout(Authentication authentication) {
        var authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
        // Update the user's refresh token to indicate they are logged out
        refreshTokenRepository.updateRefreshTokenByResource(
                authenticatedUser.role().name(),
                authenticatedUser.userId(),
                true);
    }

    /**
     * Logs out the user by updating their refresh token to indicate they are logged out.
     * @param logoutDTO The logout request containing the user's roles and sub.
     */
    @Override
    public void logout(LogoutDTO logoutDTO) {
        // Update the user's refresh token to indicate they are logged out
        refreshTokenRepository.updateRefreshTokenByResource(
                logoutDTO.roles(),
                Integer.parseInt(logoutDTO.sub()),
                true);
    }

}
