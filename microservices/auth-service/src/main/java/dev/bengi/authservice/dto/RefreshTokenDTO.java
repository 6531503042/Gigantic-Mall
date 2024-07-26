package dev.bengi.authservice.dto;

/**
 *
 * @param usage
 * @param resourceId
 * @param refreshToken
 * @author bengi
 */
public record RefreshTokenDTO(
        String usage,
        Long resourceId,
        String refreshToken
) {
}
