package dev.bengi.authservice.service.Impl;

import dev.bengi.authservice.model.UserLogin;
import dev.bengi.authservice.repository.UserLoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.bengi.authservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import dev.bengi.authservice.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final UserLoginRepository userLoginRepository;
    //    private final UserRepository userRepository;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserLoginRepository userLoginRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userLoginRepository = userLoginRepository;
    }


    public Optional<UserLogin> findCredentialByEmail(String email) {
        var user = userLoginRepository.findOneByEmail(email);

        return user;
    }

}
