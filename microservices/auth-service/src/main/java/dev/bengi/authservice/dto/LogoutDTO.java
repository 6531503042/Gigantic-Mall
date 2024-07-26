package dev.bengi.authservice.dto;

/**
 *
 * @param sub
 * @param roles
 * @author bengi
 */
public record LogoutDTO(
        String sub,
        String roles
) {
}
