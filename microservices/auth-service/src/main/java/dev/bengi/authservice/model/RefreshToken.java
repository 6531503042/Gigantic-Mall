package dev.bengi.authservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("refresh_tokens")
public record RefreshToken(
        @Id
        Integer id,
        String token,
        Instant issuedAt,
        String usage,
        Long resourceId,
        boolean isExpired) {
}
