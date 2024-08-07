package dev.bengi.walletservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * @author s.bengi
 * @param amount
 * @param userId
 */
public record TopupDTO(
        @DecimalMin(value = "0.0", inclusive = false) @NotNull BigDecimal amount,
        Integer userId
) {
}
