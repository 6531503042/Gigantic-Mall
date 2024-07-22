package dev.bengi.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("roles")
public record Role(
        @Id
        Integer id,
        Integer level,
        String name) {
}