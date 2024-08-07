package dev.bengi.walletservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import dev.bengi.userservice.model.User;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author s.bengi
 * @param id
 * @param userId
 * @param lastUpdated
 * @param balance
 */
@Table("user_wallet")
public record Wallet(
        @Id
        String id,

        // Called by JDBC as "userId" AggregateReference
        AggregateReference<User, Integer> userId,
        Instant lastUpdated,

        //Used BigDecimal to avoid precision loss
        BigDecimal balance
) {
}
