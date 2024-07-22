package com.gigantic.wallet.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.gigantic.shopCompany.model.ShopCompany;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "shop_company_wallets")
public record ShopCompanyWaller(
        @Id Integer id,
        AggregateReference<ShopCompany, Integer> shopCompanyId,
        Instant lastUpdated,
        BigDecimal balance
) {
}
