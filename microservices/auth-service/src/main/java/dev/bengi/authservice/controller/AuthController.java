package dev.bengi.authservice.controller;

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

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     *
     * @param body
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginResponseDTO body) {
        return ResponseEntity.ok(authService.login(body));
    }

    /**
     *
     * @param body
     * @return
     */
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody @Validated RefreshTokenDTO body) {
        return ResponseEntity.ok(authService.issueNewAccessToken(body));
    }

    /**
     *
     * @param authentication
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication) {
        var jwt = (Jwt) authentication.getPrincipal();
        var logoutDTO = new LogoutDTO(jwt.getClaimAsString("sub"), jwt.getClaimAsString("roles"));
        authService.logout(logoutDTO);
        return ResponseEntity.noContent().build();
    }


}
