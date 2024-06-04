package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category);

    Category createRootCategory(String name, String alias);

    Category createSubCategory(String name, Long parentId) throws CategoryNotFoundException;

    Set<Category> getChildren(Long id) throws CategoryNotFoundException;

    List<Category> listAll();

    void deleteCategory(Long categoryId) throws ResourceNotFoundException;

    List<Category> listRootCategory();
}
