package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.CategoryService;
import com.gigantic.entity.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category createRootCategory(String name) {
        Category category =  new Category();
        category.setName(name);
        return repo.save(category);
    }

    @Override
    public Category createSubCategory(String name, Long parentId) {
        Category parentCategory = repo.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent category not found"));
        Category category =  new Category();
        category.setName(name);
        category.setParent(parentCategory);
        parentCategory.getChildren().add(category);
        return repo.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Category getAllCategories() {
        return repo.findAllCategories();
    }


}
