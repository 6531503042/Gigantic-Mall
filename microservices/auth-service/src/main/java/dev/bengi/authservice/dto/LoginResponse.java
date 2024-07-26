package dev.bengi.authservice.dto;

/**
 *
 * @param id
 * @param tokenType
 * @param accessToken
 * @param refreshToken
 * @author bengi
 *
 */
public record LoginResponse(
        Integer id,
        String tokenType,
        String accessToken,
        String refreshToken
) {
}
