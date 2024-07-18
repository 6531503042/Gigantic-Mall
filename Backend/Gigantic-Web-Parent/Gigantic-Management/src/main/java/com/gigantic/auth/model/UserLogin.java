package com.gigantic.auth.model;

import com.gigantic.entity.User;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_login")
public record UserLogin  (
        @Id
        Long id,
        AggregateReference<User, Long> userId,
        String email,
        String password) {
}
