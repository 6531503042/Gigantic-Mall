package com.gigantic.admin.Config;

import com.gigantic.entity.Brand;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecificationConfig {

    public static Specification<Brand> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Brand> containKeywords(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%")
                );
    }
}
