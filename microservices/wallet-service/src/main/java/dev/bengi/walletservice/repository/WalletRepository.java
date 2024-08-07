package dev.bengi.walletservice.repository;

import dev.bengi.walletservice.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author s.bengi
 */
@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer> {
}
