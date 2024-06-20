package com.gigantic.admin.Controller;

import com.gigantic.DTO.ProductDTO;
import com.gigantic.admin.Exception.DuplicateProductException;
import com.gigantic.admin.Repository.ProductRepository;
import com.gigantic.admin.Service.Impl.ProductServiceImpl;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import com.gigantic.entity.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) throws DuplicateProductException {

        try {
            // Checking if productDTO is null
            if (productDTO == null) {
                // throw exception if productDTO is null
                throw new IllegalAccessException("Product cannot be null");
            }

            // Checking if parameter categoryId, brandId is null
            if (productDTO.getCategoryId() == null || productDTO.getBrandId() == null) {
                throw new IllegalAccessException("Category or Brand cannot be null");
            }

            Product existingProduct = repo.findByName(productDTO.getName());
            if (existingProduct != null) {
                throw new DuplicateProductException("Product already exists");
            }

        } catch (EmptyResultDataAccessException | IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setAlias(productDTO.getAlias());
        product.setShortDescription(productDTO.getShortDescription());
        product.setFullDescription(productDTO.getFullDescription());
        product.setCreatedTime(productDTO.getCreatedTime() != null ? productDTO.getCreatedTime() : new Date());
        product.setUpdatedTime(productDTO.getUpdatedTime());
        product.setInStock(productDTO.isInStock());
        product.setStatus(productDTO.isStatus());
        product.setCost(productDTO.getCost());
        product.setPrice(productDTO.getPrice());
        product.setDiscountPercent(productDTO.getDiscountPercent());
        product.setLength(productDTO.getLength());
        product.setWidth(productDTO.getWidth());
        product.setHeight(productDTO.getHeight());
        product.setWeight(productDTO.getWeight());

        Set<Category> categories = new HashSet<>();
        for (Long categoryId : productDTO.getCategoryId()) {
            Category category = new Category();
            category.setId(categoryId);
            categories.add(category);
        }
        product.setCategories(categories);

        Set<Brand> brands = new HashSet<>();
        for (Long brandId : productDTO.getBrandId()) {
            Brand brand = new Brand();
            brand.setId(brandId);
            brands.add(brand);
        }
        product.setBrands(brands);

        Product createdProduct = services.save(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/update/status/{productId}")
    public ResponseEntity<Product> updateProductStatus(@PathVariable Long productId, @RequestBody Boolean status) throws Exception {
        Product updatedProduct = services.updatedProductStatus(productId, status);
        return ResponseEntity.ok(updatedProduct);
    }
}
