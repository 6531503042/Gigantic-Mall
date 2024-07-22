package com.gigantic.auth.dto;

public record RefreshTokenDTO(
        String usage,
        Long resourceId,
        String refreshToken) {
}
