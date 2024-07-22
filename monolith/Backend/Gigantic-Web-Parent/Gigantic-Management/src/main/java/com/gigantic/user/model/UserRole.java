package com.gigantic.user.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_roles")
public record UserRole(
        @Id
        Integer id,
        AggregateReference<User, Integer> userId,
        AggregateReference<Role, Integer> roleId
) {
}
