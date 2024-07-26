package dev.bengi.authservice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @param email
 * @param passwords
 * @author bengi
 */
public record LoginRequestDTO(
        @NotBlank String email,
        @NotBlank String passwords
) {
}
