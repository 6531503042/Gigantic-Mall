package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.DuplicateProductException;
import com.gigantic.admin.Exception.ProductNotFoundException;
import com.gigantic.admin.Repository.ProductRepository;
import com.gigantic.admin.Service.Impl.ProductServiceImpl;
import com.gigantic.entity.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl services;

    @Autowired
    private ProductRepository repo;

    @GetMapping("/api")
    public String getProductPage() {
        return "Product-API is Working :D";
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws DuplicateProductException {

        try {

            //Checking if product is null
            if (product == null) {
                //throw exception if product is null
                throw new IllegalAccessException("Product cannot be null");
            }

            //Checking if parameter category_id , brand_id is null
            if (product.getCategory_id() == null || product.getBrand_id() == null) {
                throw new IllegalAccessException("Category or Brand cannot be null");
            }

            Product exisingProduct = repo.findByName(product.getName());
            if (exisingProduct != null) {
                throw new DuplicateProductException("Product already exists");
            }

        } catch (EmptyResultDataAccessException | IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (product.getId() == null) {
            product.setCreatedTime(new Date());
        }

        Product createdProduct = services.save(product);
        return ResponseEntity.ok(createdProduct);
    }


    @PutMapping("/update/status/{productId}")
    public ResponseEntity<Product> updateProductStatus(@PathVariable Long productId, @RequestBody Boolean status) throws Exception {
        Product updatedProduct = services.updatedProductStatus(productId, status);
        return ResponseEntity.ok(updatedProduct);
    }
}
