package com.gigantic.user.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public record UserCreatedDTO(
        @NotBlank  String firstName,
        @NotBlank String lastName,
        Date dateCreated,
        String phoneNumber,
        @NotBlank String email,
        @NotBlank String password
) {
}
