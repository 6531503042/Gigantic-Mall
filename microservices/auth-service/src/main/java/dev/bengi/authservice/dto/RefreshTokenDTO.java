package dev.bengi.authservice.dto;

public record RefreshTokenDTO(
        String usage,
        Long resourceId,
        String refreshToken
) {
}
