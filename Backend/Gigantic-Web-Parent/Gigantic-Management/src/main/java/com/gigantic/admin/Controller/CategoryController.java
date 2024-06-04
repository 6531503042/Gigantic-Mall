package com.gigantic.admin.Controller;

import com.gigantic.DTO.RootCategoryRequest;
import com.gigantic.DTO.SubCategoryRequest;
import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.Impl.CategoryServiceImpl;
import com.gigantic.entity.Category;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping("/list")
//    public ResponseEntity<List<Category>> getAllCategories() {
//        List<Category> categories = (List<Category>) services.getAllCategories();
//        if (categories.isEmpty()) {
//            throw new RuntimeException("Categories not found");
//        }
//        return ResponseEntity.ok(categories);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
//        return ResponseEntity.ok(services.getCategoryById(id));
//    }



//    @PostMapping("/root")
//    public Category createRootCategory(@RequestParam String name) {
//        Category category = new Category();
//        category.setName(name);
//        category.setAlias(name);
//        category.setImage("default.png");
//        return repo.save(category);
//    }

    @GetMapping("/list")
    public List<Category> listAll() {
        return services.listAll();
    }

    @PostMapping("/root")
    public Category createRootCategory(@RequestBody RootCategoryRequest request) {
        return services.createRootCategory(request.getName(), request.getAlias());
    }

    @PostMapping("/sub")
    public ResponseEntity<Category> createSubCategory(@RequestParam SubCategoryRequest request) throws CategoryNotFoundException {
        Category category = services.createSubCategory(request.getName(), request.getParentId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }



//    @PostMapping("/root")
//    public Category createRootCategory(@RequestBody RootCategoryRequest request) {
//        return services.createRootCategory(request.getName(), request.getAlias());
//    }

//    @PostMapping("/sub")
//    public ResponseEntity<Category> createSubCategory(@RequestParam String name, @RequestParam Long parentId) {
//        Category category = services.createSubCategory(name, parentId);
//        return new ResponseEntity<>(category, HttpStatus.CREATED);
//    }






}
