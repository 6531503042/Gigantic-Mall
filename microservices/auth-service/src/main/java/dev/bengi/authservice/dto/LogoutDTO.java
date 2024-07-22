package dev.bengi.authservice.dto;

public record LogoutDTO(
        String sub,
        String roles
) {
}
