package com.gigantic.user.dto;

import javax.validation.constraints.NotBlank;

public record UserUpdateDTO(
        @NotBlank String firstName,
        @NotBlank String lastName) {
}
