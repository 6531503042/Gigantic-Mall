package dev.bengi.authservice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import dev.bengi.authservice.model.UserLogin;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {

    @Query("SELECT u FROM UserLogin u WHERE u.email = ?1")
    Optional<UserLogin> findOneByEmail(String email);
}
