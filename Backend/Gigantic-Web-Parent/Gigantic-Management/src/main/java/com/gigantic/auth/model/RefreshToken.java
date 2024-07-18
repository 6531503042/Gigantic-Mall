package com.gigantic.auth.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "refresh_tokens")
public record RefreshToken(
        @Id Long id,
        String token,
        Instant issuedAt,
        String usage,
        Long resourceId,
        boolean isExpired) {
}
