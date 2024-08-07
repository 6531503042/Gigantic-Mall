package dev.bengi.walletservice.service.impl;

import dev.bengi.userservice.model.User;
import dev.bengi.walletservice.model.Wallet;
import dev.bengi.walletservice.repository.WalletRepository;
import dev.bengi.walletservice.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class WalletServiceImpl implements WalletService {

    private final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    //Injection as Constructor
    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    /**
     *
     * createConsumerWallet
     * @param userId
     */
    @Override
    public Wallet createConsumerWallet(int userId) {

        AggregateReference<User, Integer> userAggregate = AggregateReference.to(userId);

        //initial currentTimestamp
        Instant currentTimestamp = Instant.now();

        //initial balance with Big decimal
        BigDecimal initBalance = BigDecimal.ZERO;

        //Initial Wallet Constructor
        var wallet = new Wallet(
                null,
                userAggregate,
                currentTimestamp,
                initBalance
        );

        //Using JDBC to save Wallet
        var newWallet = walletRepository.save(wallet);

        //logging for backend testing
        logger.info("Created wallet for user: {}", userId);
        return newWallet;
    }



}
