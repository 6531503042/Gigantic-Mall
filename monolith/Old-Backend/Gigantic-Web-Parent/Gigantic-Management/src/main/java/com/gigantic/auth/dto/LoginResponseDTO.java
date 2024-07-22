package com.gigantic.auth.dto;

public record LoginResponseDTO(
        Long userId,
        String tokenType,
        String accessToken,
        String refreshToken) {
}
