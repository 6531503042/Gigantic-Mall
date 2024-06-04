package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.CategoryService;
import com.gigantic.entity.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static javax.swing.UIManager.get;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category saveCategory(Category category) {
        return repo.save(category);
    }

    @Override
    public Category createRootCategory(String name, String alias) {
        Category category = new Category();
        category.setName(name);
        category.setAlias(alias);
        category.setImage("default.png");
        category.setEnabled(true);
        return repo.save(category);
    }

    @Override
    public Category createSubCategory(String name, Long parentId) throws CategoryNotFoundException {
        Category parentCategory = repo.findById(parentId)
                .orElseThrow(() -> new CategoryNotFoundException("Parent category not found"));
        Category subCategory =  new Category();
        subCategory.setName(name);
        subCategory.setParent(parentCategory);
        subCategory.setEnabled(true);
//        parentCategory.getChildren().add(subCategory);
        return repo.save(subCategory);
    }

//    @Override
//    public Category getParentCategory(Long id) {
//        return repo.findById(id).get();
//    }

    @Override
    public Set<Category> getChildren(Long id) throws CategoryNotFoundException{
        Category category = (Category) get(id);
        return category.getChildren();
    }

    @Override
    public List<Category> listAll() {
        return (List<Category>) repo.findAllCategories();
    }

    @Override
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        Category category = repo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        for (Category child : category.getChildren()) {
            child.setParent(null);
            repo.save(child);
        }

        repo.delete(category);
    }

    @Override
    public List<Category> listRootCategory() {
        return repo.findByParentIsNull();
    }




}
