
package com.gigantic.brand.controller;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.brand.repository.BrandRepository;
import com.gigantic.brand.services.BrandService;
import com.gigantic.category.repository.CategoryRepository;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {



    private final BrandService services;


    @Autowired
    public BrandController(BrandService services) {
        this.services = services;
    }


    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/")
    public String hello() {
        return "brand-api working !";
    }

    @GetMapping("/list")
    public ResponseEntity<String> getBrandList(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) String keyword) {
        List<Brand> brands = services.listAll(name, sortDirection, sortField, keyword);
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("There'll no brands found");
        }
        return ResponseEntity.ok(brands.toString());
    }



    @PostMapping("/create")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) throws Exception {

        // Ensure that the Category entities referenced by the Brand are already saved
        Set<Category> categories = brand.getCategories().stream()
                .map(category -> {
                    try {
                        return categoryRepository.findById(category.getId())
                                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
                    } catch (CategoryNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
        brand.setCategories(categories);

        Brand newBrand = services.save(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBrand);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brand) throws Exception {

        // Extract only the categoryId from each category
        if (brand.getCategories() != null) {
            Set<Long> categoryIds = brand.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            brand.setCategoryIds(Collections.singleton(categoryIds));
        }
        Brand updatedBrand = services.updateBrand(id, brand, (Category) categoryRepository);
        return ResponseEntity.ok(updatedBrand);
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<Brand> updateBrandStatus(@PathVariable Long id, @RequestBody Boolean status) throws Exception {
        Brand updatedBrand = services.updatedBrandStatus(id, status);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Brand> deleteBrand(@RequestBody Brand brand) throws Exception {
        Brand deletedBrand = services.deleteBrand(brand);
        return ResponseEntity.ok(deletedBrand);
    }
}
