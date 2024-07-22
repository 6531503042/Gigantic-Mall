package com.example.shopservice.service.impl;

import com.example.shopservice.dto.ProductDto;
import com.example.shopservice.dto.ProductUpdateDto;
import com.example.shopservice.model.Product;
import com.example.shopservice.repository.ProductRepository;
import com.example.shopservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product(null, productDto.name(), productDto.image(), productDto.detail());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        Product updatedProduct = new Product(id, productUpdateDto.name(), productUpdateDto.image(), productUpdateDto.detail());
        return productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
