package com.gigantic.user.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "roles")
public record Role(
        @Id
        Integer id,
        Integer level,
        String name) {
}
