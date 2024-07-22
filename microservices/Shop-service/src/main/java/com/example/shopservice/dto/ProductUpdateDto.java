package com.example.shopservice.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductUpdateDto(
     @NotBlank String name, 
     @NotBlank String image, 
     @NotBlank String detail) {
}

