package dev.bengi.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String email,
    String phoneNumber
) {
}
