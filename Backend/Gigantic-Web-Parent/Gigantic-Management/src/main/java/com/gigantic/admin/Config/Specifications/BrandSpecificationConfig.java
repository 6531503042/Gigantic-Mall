package com.gigantic.admin.Config.Specifications;

import com.gigantic.entity.Brand;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

public class BrandSpecificationConfig {

    public static Specification<Brand> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Brand> containKeywords(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
    }

    public static Specification<Brand> withCategories() {
        return (root, query, builder) -> {
            root.fetch("categories", JoinType.LEFT);
            return builder.conjunction();
        };
    }
}
