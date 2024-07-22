package com.example.shopservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record Product(
    @Id
    String id,
    String name,
    String image,
    String detail) {

}
