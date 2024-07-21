package dev.bengi.userservice.model;


import javax.persistence.Id;
import javax.persistence.Table;

@Table (name = "roles")
public record role (

        @Id
        Integer id,
        Integer level,
        String name
) {

}