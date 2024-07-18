package com.gigantic.entity;

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
