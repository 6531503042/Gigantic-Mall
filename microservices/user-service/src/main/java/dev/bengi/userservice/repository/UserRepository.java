package dev.bengi.userservice.repository;

import dev.bengi.userservice.model.User;
import dev.bengi.userservice.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
    void delete(UserRole userRole);

    Page<User> findByFirstNameContaining(String keyword, Pageable pageable);
}
