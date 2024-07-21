package dev.bengi.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("users")
public record User(
        @Id
        Integer id,
        String firstName,
        String lastName,
        String email,
        String password,
        Date createdAt,
        String phoneNumber,
        boolean status) {
}
