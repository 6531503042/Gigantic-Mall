package dev.bengi.authservice.service;

import dev.bengi.authservice.model.UserLogin;

import java.util.Optional;

public interface AuthService {
    Optional<UserLogin> findCredentialByEmail(String email);
}
