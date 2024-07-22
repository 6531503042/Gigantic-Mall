package com.gigantic.wallet.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import com.gigantic.user.model.User;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.math.BigDecimal;

@Table(name = "user_wallets")
public record UserWallets(
        @Id
    Integer id,
        AggregateReference<User, Integer> userId,
        Instant lastUpdated,
        BigDecimal balance) {
}
