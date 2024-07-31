package dev.bengi.authservice.service;

import dev.bengi.authservice.dto.AuthenticatedUser;
import dev.bengi.authservice.model.RefreshToken;
import dev.bengi.authservice.model.UserLogin;
import dev.bengi.authservice.repository.RefreshTokenRepository;
import dev.bengi.authservice.service.Impl.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.time.Instant;
import java.util.*;

/**
 * This class is responsible for generating and managing tokens.
 * It generates access tokens and refresh tokens for authenticated users.
 *
 * @author bengi
 */
@Service
public class TokenService {

    // Constants
    private static final String ISSUER = "mall-backend";
    private static final String ROLES_CLAIM = "roles";
    private static final int TIME_FOR_ROTATE_SECONDS = 120;

    // Dependencies
    private final JwtEncoder jwtEncoder; // For encoding JWT tokens
    private final long accessTokenExpiredInSeconds; // Time for access token to expire in seconds
    private final long refreshTokenExpiredInSeconds; // Time for refresh token to expire in seconds
    private final CustomerUserDetailsService customerUserDetailsService; // For loading user details
    private final RefreshTokenRepository refreshTokenRepository; // For managing refresh tokens

    /**
     * Constructs a new TokenService instance.
     *
     * @param jwtEncoder The JWT encoder
     * @param accessTokenExpiredInSeconds The time for access token to expire in seconds
     * @param refreshTokenExpiredInSeconds The time for refresh token to expire in seconds
     * @param customerUserDetailsService The customer user details service
     * @param refreshTokenRepository The refresh token repository
     */
    @Autowired
    public TokenService(JwtEncoder jwtEncoder, long accessTokenExpiredInSeconds, long refreshTokenExpiredInSeconds, CustomerUserDetailsService customerUserDetailsService, RefreshTokenRepository refreshTokenRepository) {
        this.jwtEncoder = jwtEncoder;
        this.accessTokenExpiredInSeconds = accessTokenExpiredInSeconds;
        this.refreshTokenExpiredInSeconds = refreshTokenExpiredInSeconds;
        this.customerUserDetailsService = customerUserDetailsService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * Issues an access token for the given Authentication.
     *
     * @param auth The Authentication object
     * @param issueDate The issue date of the token
     * @return The generated access token
     */
    public String issueAccessToken(Authentication auth, Instant issueDate) {
        return generateToken(auth, issueDate, accessTokenExpiredInSeconds);
    }

    /**
     * Issues an access token for the given UserLogin.
     *
     * @param userLogin The UserLogin object
     * @param issueDate The issue date of the token
     * @return The generated access token
     */
    public String issueAccessToken(UserLogin userLogin, Instant issueDate) {
        AuthenticatedUser userDetails = (AuthenticatedUser) customerUserDetailsService
                .loadUserByUsername(userLogin.email());
        return generateToken(userDetails, issueDate, accessTokenExpiredInSeconds);
    }

    /**
     *
     * @Author: bengi, softgi
     * wait for store - service ready
     */
//    public String issueAccessToken(Store storeLogin, Instant issueDate) {
//        AuthenticatedUser userDetails = (AuthenticatedUser) customUserDetailsService
//                .loadUserByUsername(storeLogin.username());
//        return generateToken(userDetails, issueDate, accessTokenExpiredInSeconds);
//    }

    public String issueRefreshToken() {
        return UUID.randomUUID().toString();
    }

    public String generateToken(AuthenticatedUser auth, Instant issueDate, long expiredInSeconds) {
        return generateToken(auth.userId(), auth.getAuthorities(), issueDate, expiredInSeconds);
    }

    public String generateToken(Authentication auth, Instant issueDate, long expiredInSeconds) {
        var authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
        return generateToken(authenticatedUser.userId(), auth.getAuthorities(), issueDate, expiredInSeconds);
    }


    /**
     * Generates a JWT token for the given user ID, authorities, issue date, and expiration time.
     *
     * @param userId the ID of the user
     * @param authorities the authorities of the user
     * @param issueDate the date the token was issued
     * @param expiredInSeconds the expiration time of the token in seconds
     * @return the generated JWT token
     */
    private String generateToken(
            Integer userId,
            Collection<? extends GrantedAuthority> authorities,
            Instant issueDate,
            long expiredInSeconds) {

        // Calculate the expiration date based on the issue date and expiration time
        Instant expire = issueDate.plusSeconds(expiredInSeconds);

        // Get the authorities as a space-separated string
        String scope = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Create the JWT claims set with the necessary information
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(ISSUER) // Set the issuer to the constant value
                .issuedAt(issueDate) // Set the issued at time to the provided issue date
                .subject(String.valueOf(userId)) // Set the subject to the user ID
                .claim(ROLES_CLAIM, scope) // Set the roles claim to the authorities string
                .expiresAt(expire) // Set the expiration time to the calculated expiration date
                .build();

        // Encode the claims to a JWT token and return it
        return encodeClaimToJwt(claims);
    }

    public String encodeClaimToJwt(JwtClaimsSet claims) {
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Rotates the refresh token if it is close to expiring.
     *
     * @param refreshTokenEntity the refresh token entity to rotate
     * @return the new refresh token if it was rotated, otherwise the original refresh token
     */
    public String rotateRefreshTokenIfNeed(RefreshToken refreshTokenEntity) {
        // Get the issued date of the refresh token
        var issuedDate = refreshTokenEntity.issuedAt();

        // Calculate the expiration date of the refresh token
        var expireDate = issuedDate.plusSeconds(refreshTokenExpiredInSeconds);

        // Calculate the threshold date for rotating the refresh token
        var thresholdToRotateDate = expireDate.minusSeconds(TIME_FOR_ROTATE_SECONDS);

        // Get the current date and time
        var now = Instant.now();

        // Check if the current date and time is after the threshold date for rotation
        if (now.isAfter(thresholdToRotateDate)) {
            // If it is, generate a new refresh token and return it
            return issueRefreshToken();
        }

        // If the current date and time is not after the threshold date for rotation, return the original refresh token
        return refreshTokenEntity.token();
    }

    /**
     * This method is used to clean up refresh tokens that have not yet expired.
     * It assumes the refresh token's lifetime is 1 day.
     * The method calculates the threshold date by subtracting the refresh token's expiration time in seconds from the current time.
     * It then updates the refresh token's status in the repository to expired.
     *
     * @return void
     */
    public void cleanupRefreshTokenThatNotExpired() {
        // Get the current time
        var now = Instant.now();

        // Calculate the threshold date by subtracting the refresh token's expiration time in seconds from the current time
        var thresholdDate = now.minusSeconds(refreshTokenExpiredInSeconds);

        // Update the refresh token's status in the repository to expired
        refreshTokenRepository.updateRefreshTokenThatExpired(true, thresholdDate);
    }

}
