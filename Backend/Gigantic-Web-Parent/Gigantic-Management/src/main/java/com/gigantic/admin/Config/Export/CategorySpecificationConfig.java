package com.gigantic.admin.Config.Export;

import com.gigantic.entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecificationConfig {

    public static Specification<Category> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Category> containsKeyword(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("alias")), "%" + keyword.toLowerCase() + "%")
                );
    }
}
