package com.example.shopservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("products")
public record Product(
    @Id
    String id,
    String name,
    String image,
    String detail) {

}
