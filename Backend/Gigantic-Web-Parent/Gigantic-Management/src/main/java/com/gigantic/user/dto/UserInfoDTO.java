package com.gigantic.user.dto;

public record UserInfoDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
