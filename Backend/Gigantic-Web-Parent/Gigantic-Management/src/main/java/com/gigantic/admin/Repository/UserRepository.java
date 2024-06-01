package com.gigantic.admin.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gigantic.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long>, JpaSpecificationExecutor<User> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getUserById(@Param("id") Long id);

    @Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
    @Modifying
    public void updateEnabledStatus(Long id, boolean enabled);

    @Query("SELECT COUNT(u) FROM User u WHERE u.id = ?1")
    public Long countById(Long id);

    //For Page React
//    @Query("Select u FROM User u WHERE CONCAT(u.id, u.email, u.firstName, u.lastName) LIKE  %?1% ")
//    public Page<User> findAll(String keyword, Pageable pageable);

}
