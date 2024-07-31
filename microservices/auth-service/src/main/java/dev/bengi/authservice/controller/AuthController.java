package dev.bengi.authservice.controller;

import dev.bengi.authservice.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import dev.bengi.authservice.service.AuthService;
import dev.bengi.authservice.dto.LoginResponseDTO;
import dev.bengi.authservice.dto.LogoutDTO;
import dev.bengi.authservice.dto.RefreshTokenDTO;

/**
 * Controller for handling authentication related endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructs a new AuthController.
     *
     * @param authService The AuthService to use for authentication.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Handles a login request.
     *
     * @param body The login request body.
     * @return The response containing the login response DTO.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginRequestDTO body) {
        return ResponseEntity.ok(authService.login(body));
    }

    /**
     * Handles a refresh token request.
     *
     * @param body The refresh token request body.
     * @return The response containing the new login response DTO.
     */
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody @Validated RefreshTokenDTO body) {
        return ResponseEntity.ok(authService.issueNewAccessToken(body));
    }

    /**
     * Handles a logout request.
     *
     * @param authentication The authentication object containing the JWT.
     * @return A response with no content.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication) {
        var jwt = (Jwt) authentication.getPrincipal();
        var logoutDTO = new LogoutDTO(jwt.getClaimAsString("sub"), jwt.getClaimAsString("roles"));
        authService.logout(logoutDTO);
        return ResponseEntity.noContent().build();
    }

}