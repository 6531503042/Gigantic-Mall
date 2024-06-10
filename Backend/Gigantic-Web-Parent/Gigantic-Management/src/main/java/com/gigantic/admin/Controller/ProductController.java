package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.DuplicateProductException;
import com.gigantic.admin.Service.Impl.ProductServiceImpl;
import com.gigantic.entity.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl services;

    @GetMapping("/api")
    public String getProductPage() {
        return "Product-API is Working :D";
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws DuplicateProductException {

        //Ensure
        Set<Product> products = (Set<Product>) product.getCategories().stream()
                .map(category -> {
                    try {
                        return services.getById(category.getId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());

        return ResponseEntity.ok(services.save(product));
    }
}
