package com.gigantic.user.dto;

import javax.validation.constraints.NotBlank;

public record UserCreatedDTO(
        @NotBlank  String firstName,
        @NotBlank String lastName,
        String phoneNumber,
        @NotBlank String email,
        @NotBlank String password
) {
}
