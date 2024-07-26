package dev.bengi.authservice.service.Impl;

import dev.bengi.authservice.dto.AuthenticatedUser;
import dev.bengi.authservice.repository.UserLoginRepository;
import dev.bengi.userservice.repository.UserRoleRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static dev.bengi.authservice.utils.AuthUtils.isEmail;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Email;


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
        return null;
    }

//    private boolean isEmail(String username) {
//        return EmailValidator.getInstance().isValid(username);
//    }

    private AuthenticatedUser userAuthenticated(String username) {
        var userLogin = userLoginRepository.findOneByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Credential for %s not found", username)));

        //Return
        return null;
    }
}
