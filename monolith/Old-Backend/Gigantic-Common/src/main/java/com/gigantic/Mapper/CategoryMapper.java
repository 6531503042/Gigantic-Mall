//package com.gigantic.Mapper;
//import com.gigantic.DTO.CategoryDTO;
//import com.gigantic.entity.Category;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.gigantic.admin.Repository.CategoryRepository;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Mapper
//public class CategoryMapper {
//
//    @Autowired
//    private CategoryRepository repo;
//
//    public CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
//    public Category toEntity(CategoryDTO dto) {
//        return toEntity(dto, new HashMap<>());
//    }
//    public Category toEntity(CategoryDTO dto, Map<Long, Category> categoryMap) {
//
//        if (categoryMap.containsKey(dto.getId())) {
//            return categoryMap.get(dto.getId());
//        }
//
//        //Constructor
//        Category category = new Category();
//        category.setId(dto.getId());
//        category.setName(dto.getName());
//        category.setAlias(dto.getAlias());
//        category.setImage(dto.getImage());
//        category.setEnabled(dto.isEnabled());
//        category.setAllParentIDs(dto.getAllParentIDs());
//
//        //check parent
//        if (dto.getParentId() != null) {
//            Category parent = repo.findById(dto.getParentId()).orElse(null);
//            category.setParent(parent);
//        }
//
//        //Check child
//        categoryMap.put(dto.getId(), category);
//
//        //Check children
//        if (dto.getChildren() != null) {
//            Set<Category> childrenEntities = dto.getChildren().stream()
//                    .map(childDto -> toEntity(childDto, categoryMap))
//                    .collect(Collectors.toSet());
//            category.setChildren(childrenEntities);
//        }
//
//        return category;
//    }
//
//    public CategoryDTO toDTO(Category category) {
//        return toDTO(category, new HashMap<>());
//    }
//
//    public CategoryDTO toDTO(Category category, Map<Long, CategoryDTO> categoryMap) {
//
//        //Check if category is already mapped
//        if (categoryMap.containsKey(category.getId())) {
//            return categoryMap.get(category.getId());
//        }
//
//        //Constructor
//        CategoryDTO dto = new CategoryDTO();
//        dto.setId(category.getId());
//        dto.setName(category.getName());
//        dto.setAlias(category.getAlias());
//        dto.setImage(category.getImage());
//        dto.setEnabled(category.isEnabled());
//        dto.setAllParentIDs(category.getAllParentIDs());
//        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
//        dto.setHasChildren(category.getChildren() != null && !category.getChildren().isEmpty());
//
//        //Check children
//        categoryMap.put(category.getId(), dto);
//
//        if (category.getChildren() != null) {
//            Set<CategoryDTO> childrenDTOs = category.getChildren().stream()
//                    .map(child -> toDTO(child, categoryMap))
//                    .collect(Collectors.toSet());
//            dto.setChildren(childrenDTOs);
//        }
//
//        return dto;
//    }
//}
//
//
