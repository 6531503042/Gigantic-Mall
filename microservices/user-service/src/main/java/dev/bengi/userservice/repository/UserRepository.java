package dev.bengi.userservice.repository;

import dev.bengi.userservice.model.User;
import dev.bengi.userservice.model.UserRole;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    void delete(UserRole userRole);
}
