
package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.DuplicateBrandException;
import com.gigantic.admin.Repository.BrandRepository;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.BrandService;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService services;


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
                    body("There're no brands found");
        }
        return ResponseEntity.ok(brands.toString());
    }



    @PostMapping("/create")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) throws Exception {
        if (brand.getId() != null) {
            throw new DuplicateBrandException("Brand already exists");
        }

        // Extract only the categoryId from each category
        if (brand.getCategories() != null) {
            Set<Long> categoryIds = brand.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            brand.setCategoryIds(categoryIds);
        }

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
            brand.setCategoryIds(categoryIds);
        }
        Brand updatedBrand = services.updateBrand(id, brand, (Category) categoryRepository);
        return ResponseEntity.ok(updatedBrand);
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<Brand> updateBrandStatus(@PathVariable Long id, @RequestBody Boolean status) throws Exception {
        Brand updatedBrand = services.updatedBrandStatus(id, status);
        return ResponseEntity.ok(updatedBrand);
    }
}
