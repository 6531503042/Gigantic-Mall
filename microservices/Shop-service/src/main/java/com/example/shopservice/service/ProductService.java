package com.example.shopservice.service;

import com.example.shopservice.dto.CreateProductDto;
import com.example.shopservice.dto.ProductUpdateDto;
import com.example.shopservice.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductDto productDto);
    Product updateProduct(String id, ProductUpdateDto productUpdateDto);
    void deleteProduct(String id);
    Product getProductById(String id);
    List<Product> getAllProducts();
}
