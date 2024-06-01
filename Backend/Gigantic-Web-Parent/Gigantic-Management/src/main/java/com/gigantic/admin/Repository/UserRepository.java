package com.gigantic.admin.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gigantic.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getUserById(@Param("id") Long id);

}
