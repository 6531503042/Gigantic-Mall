package dev.bengi.walletservice.dto;

import java.math.BigDecimal;

/**
 * @author s.bengi
 * @param userId
 * @param balance
 */
public record UserWalletInfo(
        Integer userId,
        BigDecimal balance
) {
}
