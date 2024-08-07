package dev.bengi.walletservice.service;

import dev.bengi.walletservice.model.Wallet;

/**
 * @author s.bengi
 * Used as a marker interface for controller layer called service as interface
 */
public interface WalletService {
    Wallet createConsumerWallet(int userId);
}
