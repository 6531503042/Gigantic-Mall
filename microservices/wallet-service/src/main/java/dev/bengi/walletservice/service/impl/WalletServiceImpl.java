package dev.bengi.walletservice.service.impl;

import dev.bengi.userservice.model.User;
import dev.bengi.walletservice.dto.TopupDTO;
import dev.bengi.walletservice.dto.UserWalletInfo;
import dev.bengi.walletservice.model.Wallet;
import dev.bengi.walletservice.repository.WalletRepository;
import dev.bengi.walletservice.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Service for creating and managing wallets for consumers.
 * @author s.bengi
 */
@Service
public class WalletServiceImpl implements WalletService {

    private final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    //Injection as Constructor
    private final WalletRepository walletRepository;

    /**
     * Constructor for WalletServiceImpl.
     *
     * @param walletRepository The repository for managing wallets.
     */
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    /**
     * Creates a new wallet for a consumer.
     *
     * @param userId The ID of the consumer.
     * @return The newly created wallet.
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

//    public void deleteConsumerWalletById(String userId) {
//
//        //AggregateReference
//        AggregateReference<User, Integer> userReference = AggregateReference.to(userId);
//
//        //created var for delete
//        var wallet = walletRepository.deleteByUserId(userReference)
//                .orElseThrow(
//                        () -> new EntityNotFoundException("Wallet not found for user: " + userId));
//        walletRepository.delete(wallet);
//
//
//    }

    @Override
    public UserWalletInfo getOwnWalletInfo(int userId) {
        var wallet = walletRepository
                .findByUserId(AggregateReference.to(userId))
                .orElseThrow(
                        () -> new EntityNotFoundException("Wallet not found for user: " + userId));
        return new UserWalletInfo(userId, wallet.balance());
    }

    @Override
    public UserWalletInfo topup(TopupDTO recreateBody) {
        return null;
    }

}