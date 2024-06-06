package com.gigantic.admin.Service.Impl;

import com.gigantic.DTO.CategoryDTO;
import com.gigantic.admin.Config.CategorySpecificationConfig;
import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.admin.Exception.DuplicateCategoryException;
import com.gigantic.admin.Exception.ResourceNotFoundException;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.CategoryService;
import com.gigantic.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static javax.swing.UIManager.get;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category getById(Long id) throws CategoryNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new CategoryNotFoundException("Could not find any category with ID " + id);
        }
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
    public void listSubHierarchical(List<Category> hierarchicalCategories, Category parent, int suLevel, String sortDir) {
        Set<Category> children = sortSubCategories(parent.getChildren(), sortDir);
        int newSubLevel = suLevel + 1;

        for (Category subCategory : children) {
            String name = "";
            for (int i = 0;i < newSubLevel; i++) {
                name += " - ";
            }

            hierarchicalCategories.add(subCategory);
            listSubHierarchical(hierarchicalCategories, subCategory, newSubLevel, sortDir);
        }
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
    public SortedSet<Category> sortSubCategories(Set<Category> children) {
        return sortSubCategories(children, "asc");
    }


    @Override
    public Category updatedCategoryEnabledstatus(Long id, boolean enabled) {
        return repo.updatedEnabledStatus(id, enabled);
    }

//    @Override
//    public Category updatedCategories(Long id, Category category) throws DuplicateCategoryException {
//        Category existingCategory = repo.findByName(category.getName());
//
//        if (existingCategory != null && !existingCategory.getId().equals(id))
//
//            throw new DuplicateCategoryException("Category already in use: " + category.getName());
//
//        if (!category.getName().isEmpty()) {
//            category.setName(category.getName());
//        }
//
//        if (!category.getParent().getId().equals(id)) {
//            Category parent = repo.findById(category.getParent().getId()).get();
//            category.setParent(parent);
//        }
//
//        return repo.save(category);
//    }

    @Override
    public Category updatedCategories(Long id, Category category) throws DuplicateCategoryException {
        Category existingCategory = repo.getById(id);

        if (category.getName() != null && !category.getName().equals(existingCategory.getName())) {
            Category duplicateCategory = repo.findByName(category.getName());
            if (duplicateCategory != null && !duplicateCategory.getId().equals(id)) {
                throw new DuplicateCategoryException("Category already in use: " + category.getName());
            }
            existingCategory.setName(category.getName());
        }
        if (category.getAlias() != null && !category.getAlias().equals(existingCategory.getAlias())) {
            existingCategory.setAlias(category.getAlias());
        }
        if (category.getParent() != null && !category.getParent().getId().equals(existingCategory.getParent().getId())) {
            Category parent = repo.getById(category.getParent().getId());
            existingCategory.setParent(parent);
        }

        return repo.save(existingCategory);
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        return null;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        return toDTO(category, new HashMap<>());
    }

    @Override
    public CategoryDTO toDTO(Category category, Map<Long, CategoryDTO> categoryMap) {

        //Check statement
        if (categoryMap.containsKey(category.getId())) {
            return categoryMap.get(category.getId());
        }

        //Based on the Constructor Category Builder
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setAlias(category.getAlias());
        dto.setImage(category.getImage());
        dto.setEnabled(category.isEnabled());
        dto.setAllParentIDs(category.getAllParentIDs());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        dto.setHasChildren(category.getChildren() != null && !category.getChildren().isEmpty());

        categoryMap.put(category.getId(), dto);
        if (category.getChildren() != null) {
            Set<CategoryDTO> childrenDTOs = category.getChildren().
                    stream().
                    map(child -> toDTO(child, categoryMap))
                    .collect(Collectors.toSet());
            dto.setChildren(childrenDTOs);
        }
        return dto;
    }
}

//    @Override
//    public Category toEntity(CategoryDTO dto) {
//        if (dto == null) return null;
//
//        Category category = new Category();
//        category.setId(dto.getId());
//        category.setName(dto.getName());
//        category.setAlias(dto.getAlias());
//        category.setImage(dto.getImage());
//        category.setEnabled(dto.isEnabled());
//        category.setAllParentIDs(dto.getAllParentIDs());
//        category.setParent(dto.getParentId() != null ? new Category(dto.getParentId()) : null);
//        category.setChildren(dto.getChildren() != null ? dto.getChildren().stream().map(this::toEntity).collect(Collectors.toSet()) : null);
//        category.setHasChildren(dto.isHasChildren());
//
//        return category;
//    }

