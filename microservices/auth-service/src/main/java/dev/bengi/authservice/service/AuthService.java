package dev.bengi.authservice.service;

import dev.bengi.authservice.dto.LoginResponseDTO;
import dev.bengi.authservice.dto.LogoutDTO;
import dev.bengi.authservice.dto.RefreshTokenDTO;
import dev.bengi.authservice.model.UserLogin;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AuthService {
    Optional<UserLogin> findCredentialByEmail(String email);

    UserLogin createConsumerCredentials(int userId, String email, String password);

    Optional<UserLogin> findCredentialByUserId(int userId);

    void deleteCredentialByUserId(int userId);

    @Transactional
    LoginResponseDTO login(LoginResponseDTO body);

    @Transactional
    LoginResponseDTO issueNewAccessToken(RefreshTokenDTO body);

    void logout(Authentication authentication);

    void logout(LogoutDTO logoutDTO);
}
