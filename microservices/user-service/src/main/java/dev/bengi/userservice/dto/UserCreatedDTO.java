package dev.bengi.userservice.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UserCreatedDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        String phoneNumber,
        @NotBlank String password,
        @NotBlank Date createdAt) {
}
