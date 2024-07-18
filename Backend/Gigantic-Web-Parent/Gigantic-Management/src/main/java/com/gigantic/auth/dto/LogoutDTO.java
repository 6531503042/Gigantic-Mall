package com.gigantic.auth.dto;

public record LogoutDTO(
        String sub,
        String roles) {
}
