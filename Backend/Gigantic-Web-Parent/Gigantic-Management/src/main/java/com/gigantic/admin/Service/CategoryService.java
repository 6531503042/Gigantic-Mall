package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.DuplicateCategoryException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.entity.Category;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;

public interface CategoryService {

    Category getById(Long id) throws CategoryNotFoundException;

    List<Category> listHierachicalCategories(List<Category> rootCategories) throws NoSuchElementException;

    Category save(Category category);

    Set<Category> getChildren(Long id) throws CategoryNotFoundException;

    List<Category> listAll(String name, String sortDirection, String sortField, String keyword);

    void deleteCategory(Long categoryId) throws ResourceNotFoundException;

    List<Category> listRootCategory();

    void listSubGierarchy(List<Category> hierarchicalCategories, Category parent, int suLevel, String sortDir);

    SortedSet<Category> sortSubCategories(Set<Category> children);

    SortedSet<Category> sortSubCategories(Set<Category> children, String sortDir);

    Category updatedCategoryEnabledstatus(Long id, boolean enabled);

    Category updatedCategories(Long id, Category category) throws DuplicateCategoryException;
}
