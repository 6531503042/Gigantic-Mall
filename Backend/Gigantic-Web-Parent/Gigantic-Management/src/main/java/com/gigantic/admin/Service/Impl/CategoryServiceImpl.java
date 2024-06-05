package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Config.Export.CategorySpecificationConfig;
import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.CategoryService;
import com.gigantic.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static javax.swing.UIManager.get;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category getById(Long id) throws CategoryNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new CategoryNotFoundException("Could not find any category with ID " + id);
        }
    }

    @Override
    public List<Category> listHierachicalCategories(List<Category> rootCategories) throws NoSuchElementException{
        List<Category> hierachicalCategories = new ArrayList<>();

        for (Category rootCategry : rootCategories) {
            hierachicalCategories.add(rootCategry);

            Set<Category> children = sortSubCategories(rootCategry.getChildren(), "asc");
        }

        return hierachicalCategories;
    }

    @Override
    public Category save(Category category) {
        Category parent = category.getParent();
        if (parent != null) {
            String allParentIds = parent.getAllParentIDs() == null ? "-" : parent.getAllParentIDs();
            allParentIds += parent.getId() + "-";
            category.setAllParentIDs(allParentIds);
        }

        return repo.save(category);
    }


    @Override
    public Set<Category> getChildren(Long id) throws CategoryNotFoundException{
        Category category = (Category) get(id);
        return category.getChildren();
    }

    @Override
    public List<Category> listAll(String name, String sortDirection, String sortField, String keyword) {
        name = (name != null) ? name : "";
        sortField = (sortField != null) ? sortField : "id";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";

        // Build sort object
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        // Build specifications
        Specification<Category> specs = Specification.where(CategorySpecificationConfig.hasName(name));
        if (keyword != null && !keyword.isEmpty()) {
            specs = specs.and(CategorySpecificationConfig.containsKeyword(keyword));
        }

        // Fetch and return sorted and filtered categories
        return repo.findAll(specs, sort);
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

    @Override
    public void listSubGierarchy(List<Category> hierarchicalCategories, Category parent, int suLevel, String sortDir) {
        Set<Category> children = sortSubCategories(parent.getChildren(), sortDir);
        int newSubLevel = suLevel + 1;

        for (Category subCategory : children) {
            String name = "";
            for (int i = 0;i < newSubLevel; i++) {
                name += " - ";
            }

            hierarchicalCategories.add(subCategory);
            listSubGierarchy(hierarchicalCategories, subCategory, newSubLevel, sortDir);
        }
    }

    @Override
    public SortedSet<Category> sortSubCategories(Set<Category> children) {
        return sortSubCategories(children, "asc");
    }

    @Override
    public SortedSet<Category> sortSubCategories(Set<Category> children, String sortDir) {
        SortedSet<Category> sorted = new TreeSet<Category>(new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                if (sortDir.equals("asc")) {
                    return c1.getName().compareTo(c2.getName());
                } else {
                    return c2.getName().compareTo(c1.getName());
                }
            }
    });

        sorted.addAll(children);
        return sorted;
    }

    @Override
    public Category updatedCategoryEnabledstatus(Long id, boolean enabled) {
        return repo.updatedEnabledStatus(id, enabled);
    }



}
