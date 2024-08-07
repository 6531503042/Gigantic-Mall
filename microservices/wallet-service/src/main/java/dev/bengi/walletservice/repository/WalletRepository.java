package dev.bengi.walletservice.repository;

import dev.bengi.userservice.model.User;
import dev.bengi.walletservice.model.Wallet;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  @author s.bengi
 */
@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer> {

    Wallet deleteByUserId(AggregateReference<User, Integer> userReference);

    Optional<Wallet> findByUserId(AggregateReference<User, Integer> userId);
}
