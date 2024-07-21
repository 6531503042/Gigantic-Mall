package dev.bengi.userservice.dto;

public record UserRetrieveDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {
}
