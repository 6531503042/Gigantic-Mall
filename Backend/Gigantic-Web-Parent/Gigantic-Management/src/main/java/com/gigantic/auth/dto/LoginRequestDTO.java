package com.gigantic.auth.dto;

import javax.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank String email,
    @NotBlank String password
) {
}
