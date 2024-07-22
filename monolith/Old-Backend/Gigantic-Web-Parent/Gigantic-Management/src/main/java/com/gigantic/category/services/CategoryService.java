package com.gigantic.category.services;

import com.gigantic.DTO.CategoryDTO;
import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.DuplicateCategoryException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.entity.Category;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public interface CategoryService {
    Category getById(Long id) throws CategoryNotFoundException;

    Category save(Category category);

    Set<Category> getChildren(Long id) throws CategoryNotFoundException;

    SortedSet<Category> sortSubCategories(Set<Category> children, String sortDir);

    List<Category> listAll(String name, String sortDirection, String sortField, String keyword);

    void listSubHierarchical(List<Category> hierarchicalCategories, Category parent, int subLevel, String sortDir);

    void deleteCategory(Long categoryId) throws ResourceNotFoundException;

    List<Category> listRootCategory();

    SortedSet<Category> sortSubCategories(Set<Category> children);

    Category updateCategoryEnabledStatus(Long id, boolean enabled) throws CategoryNotFoundException;

    Category updateCategories(Long id, CategoryDTO categoryDTO) throws DuplicateCategoryException, CategoryNotFoundException;

    Category toEntity(CategoryDTO dto);

    CategoryDTO toDTO(Category category);
}
