package com.gigantic.admin.Service;

import com.gigantic.entity.Category;

public interface CategoryService {
    Category createRootCategory(String name);

    Category createSubCategory(String name, Long parentId);

    Category getCategoryById(Long id);

    Category getParentCategory(Long id);

    Category getAllCategories();
}
