package dev.bengi.authservice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @param email
 * @param password
 * @author bengi
 */
public record LoginRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
