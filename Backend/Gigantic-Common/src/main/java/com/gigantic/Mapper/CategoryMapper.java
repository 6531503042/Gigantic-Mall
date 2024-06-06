package com.gigantic.Mapper;
import com.gigantic.DTO.CategoryDTO;
import com.gigantic.entity.Category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping (source = "parent.id", target = "parentID")
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
