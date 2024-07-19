package com.gigantic.user.repository;

import com.gigantic.user.model.User;
import com.gigantic.user.model.UserRole;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Query("SELECT u FROM UserRole u WHERE u.userId = 1?")
    Optional<UserRole> findOneByUserId(AggregateReference<User, Integer> userId);

}
