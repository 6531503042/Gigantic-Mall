package com.gigantic.entity;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_login")
public record UserLogin  (
    @Id
    Long id,
    AggregateReference<SecurityProperties.User, Long> userId,
    String email,
    String password) {
}
