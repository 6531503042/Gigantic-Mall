package dev.bengi.userservice.repository;

import dev.bengi.userservice.model.Role;
import dev.bengi.userservice.model.UserRole;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.bengi.userservice.model.User;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Query("SELECT * FROM user_roles WHERE user_id = ?1")
    Optional<UserRole> findAllByUserId(AggregateReference<Object, Integer> objectIntegerAggregateReference);

    Optional<UserRole> findOneByUserId(AggregateReference<User, Integer> userId);

    Optional<UserRole> findByUserIdAndRoleId(AggregateReference<User, Integer> userRef, AggregateReference<Role, Integer> roleRef);
}
