package dev.bengi.userservice.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;

@Entity
@Table("user_roles")
public record UserRole(
        @Id
        Integer id,
        AggregateReference<User, Integer> userId,
        AggregateReference<Role, Integer> roleId
) {
}