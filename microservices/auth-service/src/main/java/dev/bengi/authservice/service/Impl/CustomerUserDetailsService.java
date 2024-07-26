package dev.bengi.authservice.service.Impl;

import dev.bengi.authservice.dto.AuthenticatedUser;
import dev.bengi.authservice.repository.UserLoginRepository;
import dev.bengi.userservice.enumeration.RoleEnum;
import dev.bengi.userservice.repository.UserRoleRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static org.apache.commons.validator.GenericValidator.isEmail;


/**
 * @CustomerUserDetailsService
 * @author bengi
 */

@Service
@Primary
public class CustomerUserDetailsService implements UserDetailsService {

    //Constructor Injection
    private final UserLoginRepository userLoginRepository;
    private final UserRoleRepository userRoleRepository;

    public CustomerUserDetailsService(UserLoginRepository userLoginRepository, UserRoleRepository userRoleRepository) {
        this.userLoginRepository = userLoginRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Validate
            //If it's had email -> UserLogin domain
            return userAuthenticated(username);
    }

//    private boolean isEmail(String username) {
//        return EmailValidator.getInstance().isValid(username);
//    }

    private AuthenticatedUser userAuthenticated(String username) {

        //Get User Login from DB (Email)
        var userLogin = userLoginRepository.findOneByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Credential for %s not found", username)));

        var userId = userLogin.userId().getId();

        //checking user role from DB/
        var userRole = userRoleRepository.findOneByUserId(AggregateReference.to(userId))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role for %s not found", username)));

        var role = RoleEnum.CUSTOMER;

        //Check if it's Admin
        if (userRole.roleId().getId() == RoleEnum.ADMIN.getId()) {
            role = RoleEnum.ADMIN;
        }

        //Return
        return new AuthenticatedUser(
                userId,
                userLogin.email(),
                userLogin.password(),
                role
        );
    }
}
