package com.gigantic.admin.Controller;

import com.gigantic.DTO.ProductDTO;
import com.gigantic.admin.Exception.DuplicateProductException;
import com.gigantic.admin.Exception.ProductNotFoundException;
import com.gigantic.admin.Repository.ProductRepository;
import com.gigantic.admin.Service.Impl.ProductServiceImpl;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import com.gigantic.entity.Product.Product;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final int PRODUCTS_PER_PAGE = 5;
    @Autowired
    private ProductServiceImpl services;

    @Autowired
    private ProductRepository repo;

    @GetMapping("/api")
    public String getProductPage() {
        return "Product-API is Working :D";
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> listAllProducts(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String sortDirection,
                                                            @RequestParam(required = false) String sortField,
                                                            @RequestParam(required = false) String keyword) {
        List<Product> products = services.listAll(name, sortDirection, sortField, keyword);

        if (products.isEmpty()) {
            throw new RuntimeException("Products not found");
        }

        List<ProductDTO> dto = products.stream()
                .map(services::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }


    @GetMapping("list/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(services.toDTO(services.getById(id)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(@RequestParam int pageNum, @RequestParam String keyword) {
        Pageable pageable = PageRequest.of(pageNum, PRODUCTS_PER_PAGE);
        Page<Product> page = repo.searchProductsByName(keyword, pageable);
        return ResponseEntity.ok(page);
    }

    /**
     * Saves the product price information.
     *
     * @param productId the ID of the product to save the price for
     * @param cost the new cost of the product
     * @param price the new price of the product
     * @param discountPercent the new discount percentage of the product
     * @return the saved product
     * @throws NotFoundException if the product with the given ID is not found
     */
    @PostMapping("/products/{productId}/price")
    public Product saveProductPrice(@PathVariable Long productId, @RequestParam double cost, @RequestParam double price, @RequestParam double discountPercent) throws NotFoundException {
        Product product = new Product(productId, cost, price, discountPercent);
        services.saveProductPrice(product);
        return product;
    }

    @PutMapping("/update/status/{productId}")
    public ResponseEntity<Product> updateProductStatus(@PathVariable Long productId, @RequestBody Boolean status) throws Exception {
        Product updatedProduct = services.updatedProductStatus(productId, status);
        return ResponseEntity.ok(updatedProduct);
    }
}
