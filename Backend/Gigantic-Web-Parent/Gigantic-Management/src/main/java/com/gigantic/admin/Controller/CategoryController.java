package com.gigantic.admin.Controller;

import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.Impl.CategoryServiceImpl;
import com.gigantic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl services;

    @Autowired
    private CategoryRepository repository;

//    @GetMapping("category/list")
//    public ResponseEntity<List<Category>> getAllCategories() {
//        List<Category> categories = (List<Category>) services.getAllCategories();
//        if (categories.isEmpty()) {
//            throw new RuntimeException("Categories not found");
//        }
//        return ResponseEntity.ok(categories);
//    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(services.getCategoryById(id));
    }

    @PostMapping("/category/root")
    public ResponseEntity<Category> createRootCategory(@RequestParam String name) {
        Category category = services.createRootCategory(name);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/category/sub")
    public ResponseEntity<Category> createSubCategory(@RequestParam String name, @RequestParam Long parentId) {
        Category category = services.createSubCategory(name, parentId);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }




}
