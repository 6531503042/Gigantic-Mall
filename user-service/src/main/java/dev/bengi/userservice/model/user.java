package dev.bengi.userservice.model;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
public record user (
        @Id
        Integer id,
        String firstName,
        String lastName,
        String email,
        String password,
        Date createdAt,
        boolean status) {
}