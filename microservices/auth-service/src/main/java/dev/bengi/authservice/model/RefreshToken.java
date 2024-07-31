package dev.bengi.authservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Record for refresh token in database
 * @author bengi
 */
@Table("refresh_tokens")
public record RefreshToken(
        @Id
        Integer id,
        String token,
        Instant issuedAt,
        String usage,
        Integer resourceId,
        boolean isExpired) {
}