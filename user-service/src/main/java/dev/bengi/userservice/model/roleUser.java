package dev.bengi.userservice.model;


import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.persistence.Id;


@Table (name = user_roles)
public record userRole(
        @Id
        Integer id,
        AggregatedReference<user, Integer> userId,
        AggregatedReference<role, Integer> roleId,
) {
}