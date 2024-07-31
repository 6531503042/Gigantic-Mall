package dev.bengi.userservice.repository;

import dev.bengi.userservice.model.User;
import dev.bengi.userservice.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {

    Page<User> findByFirstNameContaining(String keyword, Pageable pageable);
}
