package dev.bengi.authservice.service.Impl;

import dev.bengi.authservice.model.UserLogin;
import dev.bengi.authservice.repository.UserLoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.bengi.authservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.bengi.userservice.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * @author bengi
 */

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UserLoginRepository userLoginRepository;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserLoginRepository userLoginRepository, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userLoginRepository = userLoginRepository;
        this.authenticationManager = authenticationManager;
    }


    /**
     * This method retrieves a user login by email.
     * @author bengi
     * @param email The email of the user login.
     * @return The user login with the provided email, if it exists.
     */
    @Override
    public Optional<UserLogin> findCredentialByEmail(String email) {
        return userLoginRepository.findOneByEmail(email);
    }

    /**
     * This method creates a new user login for a customer.
     * @author bengi
     * @param userId The ID of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The created user login.
     */
    @Override
    public UserLogin createCustomerCredentials(int userId, String email, String password) {
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
     * This method retrieves a user login by user ID.
     * @author bengi
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
     * This method deletes a user login by user ID.
     * @author bengi
     * @param userId The ID of the user.
     */
    @Override
    public void deleteCredentialByUserId(int userId) {
        var credential = findCredentialByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Credential for %s not found", userId)));
        userLoginRepository.delete(credential);
    }

}
