package com.gigantic.auth.services;

import com.gigantic.user.repository.UserRoleRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CustomUserDetailsService {

    //Injection Constructor
    private final UserRoleRepository userRoleRepository;

    public CustomUserDetailsService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
}
