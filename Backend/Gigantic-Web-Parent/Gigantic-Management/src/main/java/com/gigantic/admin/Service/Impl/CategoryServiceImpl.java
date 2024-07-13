package com.gigantic.admin.Service.Impl;

import com.gigantic.DTO.CategoryDTO;
import com.gigantic.admin.Config.Specifications.CategorySpecificationConfig;
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

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;


    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category getById(Long id) throws CategoryNotFoundException {
        return repo.findById(id).orElseThrow(() -> new CategoryNotFoundException("Could not find any category with ID " + id));
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
    public Set<Category> getChildren(Long id) throws CategoryNotFoundException {
        Category category = getById(id);
        return category.getChildren();
    }

    @Override
    public SortedSet<Category> sortSubCategories(Set<Category> children, String sortDir) {
        return children.stream()
                .sorted((c1, c2) -> sortDir.equals("asc") ? c1.getName().compareTo(c2.getName()) : c2.getName().compareTo(c1.getName()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public List<Category> listAll(String name, String sortDirection, String sortField, String keyword) {
        name = (name != null) ? name : "";
        sortField = (sortField != null) ? sortField : "id";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Specification<Category> specs = Specification.where(CategorySpecificationConfig.hasName(name));

        if (keyword != null && !keyword.isEmpty()) {
            specs = specs.and(CategorySpecificationConfig.containsKeyword(keyword));
        }

        return repo.findAll(specs, sort);
    }


    @Override
    public void listSubHierarchical(List<Category> hierarchicalCategories, Category parent, int subLevel, String sortDir) {
        Set<Category> children = sortSubCategories(parent.getChildren(), sortDir);
        int newSubLevel = subLevel + 1;

        for (Category subCategory : children) {
            hierarchicalCategories.add(subCategory);
            listSubHierarchical(hierarchicalCategories, subCategory, newSubLevel, sortDir);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        CategoryDTO categoryDTO = repo.findById(categoryId)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        for (CategoryDTO child : categoryDTO.getChildren()) {
            child.setParentId(null);
            repo.save(toEntity(child));
        }

        repo.delete(toEntity(categoryDTO));
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
    public Category updateCategoryEnabledStatus(Long id, boolean enabled) throws CategoryNotFoundException {
        Category category = getById(id);
        category.setEnabled(enabled);
        return repo.save(category);
    }

    @Override
    public Category updateCategories(Long id, CategoryDTO categoryDTO) throws DuplicateCategoryException, CategoryNotFoundException {
        Category existingCategory = getById(id);

        if (categoryDTO.getName() != null && !categoryDTO.getName().equals(existingCategory.getName())) {
            Category duplicateCategory = repo.findByName(categoryDTO.getName());
            if (duplicateCategory != null && !duplicateCategory.getId().equals(id)) {
                throw new DuplicateCategoryException("Category name already in use: " + categoryDTO.getName());
            }
            existingCategory.setName(categoryDTO.getName());
        }

        if (categoryDTO.getAlias() != null && !categoryDTO.getAlias().equals(existingCategory.getAlias())) {
            existingCategory.setAlias(categoryDTO.getAlias());
        }

        if (categoryDTO.getParentId() != null) {
            Category parent = repo.findById(categoryDTO.getParentId()).orElseThrow(() -> new CategoryNotFoundException("Parent category not found"));
            existingCategory.setParent(parent);
        } else {
            existingCategory.setParent(null);
        }

        existingCategory.setEnabled(categoryDTO.isEnabled());
        existingCategory.setImage(categoryDTO.getImage());

        return repo.save(existingCategory);
    }



    @Override
    public Category toEntity(CategoryDTO dto) {
        return toEntity(dto, new HashMap<>());
    }

    private Category toEntity(CategoryDTO dto, Map<Long, Category> processed) {
        if (processed.containsKey(dto.getId())) {
            return processed.get(dto.getId());
        }

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setAlias(dto.getAlias());
        category.setImage(dto.getImage());
        category.setEnabled(dto.isEnabled());
        category.setAllParentIDs(dto.getAllParentIDs());

        if (dto.getParentId() != null) {
            Category parent = repo.findById(dto.getParentId()).orElse(null);
            category.setParent(parent);
        }

        processed.put(dto.getId(), category);

        if (dto.getChildren() != null) {
            Set<Category> childrenEntities = dto.getChildren().stream()
                    .map(childDto -> toEntity(childDto, processed))
                    .collect(Collectors.toSet());
            category.setChildren(childrenEntities);
        }

        return category;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        return toDTO(category, new HashMap<>());
    }

    private CategoryDTO toDTO(Category category, Map<Long, CategoryDTO> processed) {
        if (processed.containsKey(category.getId())) {
            return processed.get(category.getId());
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setAlias(category.getAlias());
        dto.setImage(category.getImage());
        dto.setEnabled(category.isEnabled());
        dto.setAllParentIDs(category.getAllParentIDs());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        dto.setHasChildren(category.getChildren() != null && !category.getChildren().isEmpty());

        processed.put(category.getId(), dto);

        if (category.getChildren() != null) {
            Set<CategoryDTO> childrenDTOs = category.getChildren().stream()
                    .map(child -> toDTO(child, processed))
                    .collect(Collectors.toSet());
            dto.setChildren(childrenDTOs);
        }

        return dto;
    }

}
