package com.example.shopservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProductDto(
     @NotBlank String name, 
     @NotBlank String image, 
     @NotBlank String detail) {
}
