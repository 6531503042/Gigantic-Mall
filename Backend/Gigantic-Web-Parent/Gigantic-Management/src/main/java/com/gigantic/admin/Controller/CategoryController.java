package com.gigantic.admin.Controller;

import com.gigantic.DTO.RootCategoryRequest;
import com.gigantic.DTO.SubCategoryRequest;
import com.gigantic.admin.Config.Export.Category.CategoryCsvExporter;
import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.Impl.CategoryServiceImpl;
import com.gigantic.entity.Category;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl services;

    @Autowired
    private CategoryRepository repo;

    @GetMapping("/")
    public String hello() {
        return "category-api working !";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(services.getById(id));
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) String keyword) {

        List<Category> categories = services.listAll(name, sortDirection, sortField, keyword);

        if (categories.isEmpty()) {
            throw new RuntimeException("Categories not found");
        }
        return ResponseEntity.ok(categories);
    }



    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) throws CategoryNotFoundException{
        return ResponseEntity.ok(services.save(category));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) throws CategoryNotFoundException {
        try {
            Category categories = services.getById(id);
            categories.setName(category.getName());
            categories.setAlias(category.getAlias());
            Optional.ofNullable(category.getParent()).ifPresent(parent -> {
                categories.setEnabled(parent.isEnabled());
                categories.setParent(parent);
            });
            return ResponseEntity.ok(services.save(categories));
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}/enable/{enabled}")
    public ResponseEntity<Category> updateCategoryEnabledStatus(Long id, boolean enabled) throws CategoryNotFoundException{
            Category categories = services.updatedCategoryEnabledstatus(id, enabled);
            return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws ResourceNotFoundException {
        services.deleteCategory(id);
        return ResponseEntity.ok("Category with id " + id + " has been deleted");
    }

    @GetMapping("/export/csv")
    public void exportToCsv(HttpServletResponse response, String name, String sortField, String sortDirection, String keywords) throws IOException {
        List<Category> listCategories = services.listAll(name, sortField, sortDirection, keywords);

        CategoryCsvExporter exporter = new CategoryCsvExporter();
        exporter.export(listCategories, response);
    }







}
