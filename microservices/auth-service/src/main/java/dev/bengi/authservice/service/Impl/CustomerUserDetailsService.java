package dev.bengi.authservice.service.Impl;

import dev.bengi.authservice.dto.AuthenticatedUser;
import dev.bengi.authservice.repository.UserLoginRepository;
import dev.bengi.userservice.enumeration.RoleEnum;
import dev.bengi.userservice.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * This class implements the UserDetailsService interface to load user details
 * for authentication. It is the primary implementation.
 *
 * @author bengi
 */
@Service
@Primary
public class CustomerUserDetailsService implements UserDetailsService {

    // Repositories for UserLogin and UserRole
    private final UserLoginRepository userLoginRepository;
    private final UserRoleRepository userRoleRepository;

    /**
     * Constructor injection for repositories.
     *
     * @param userLoginRepository Repository for UserLogin
     * @param userRoleRepository Repository for UserRole
     */
    @Autowired
    public CustomerUserDetailsService(UserLoginRepository userLoginRepository, UserRoleRepository userRoleRepository) {
        this.userLoginRepository = userLoginRepository;
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * This method loads user details by username.
     *
     * @param username The username of the user
     * @return UserDetails object for the user
     * @throws UsernameNotFoundException If the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Validate that the username is an email
        // If it's an email, we can assume it's a UserLogin domain
        return userAuthenticated(username);
    }

    /**
     * This method retrieves the authenticated user details.
     *
     * @param username The username of the user
     * @return AuthenticatedUser object for the user
     * @throws EntityNotFoundException If the user or role is not found
     */
    private AuthenticatedUser userAuthenticated(String username) {

        // Get the UserLogin from the database by email
        var userLogin = userLoginRepository.findOneByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Credential for %s not found", username)));

        var userId = userLogin.userId().getId();

        // Check the user role from the database
        var userRole = userRoleRepository.findOneByUserId(AggregateReference.to(userId))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role for %s not found", username)));

        var role = RoleEnum.CUSTOMER;

        // Check if it's an Admin role
        if (userRole.roleId().getId() == RoleEnum.ADMIN.getId()) {
            role = RoleEnum.ADMIN;
        }

        // Return the AuthenticatedUser object
        return new AuthenticatedUser(
                userId,
                userLogin.email(),
                userLogin.password(),
                role
        );
    }
}