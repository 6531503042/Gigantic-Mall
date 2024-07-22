package dev.bengi.authservice.dto;

public record LoginResponse(
        Integer id,
        String tokenType,
        String accessToken,
        String refreshToken
) {
}
