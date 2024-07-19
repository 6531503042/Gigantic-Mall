package com.gigantic.auth.services;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import com.gigantic.auth.services.CustomUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    //Injection Constructor
    private final JwtEncoder jwtEncoder;
    private final long accessTokenExpiredTime = 300;
    private final long refreshTokenExpiredTime = 600;
    private final CustomUserDetailsService userDetailsService;

    public TokenService(JwtEncoder jwtEncoder, CustomUserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.userDetailsService = userDetailsService;
    }
}
