package com.gigantic.product.config;

import com.gigantic.entity.Product.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationConfig {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            String keywordPattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keywordPattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), keywordPattern)
            );
        };
    }

    public static Specification<Product> sortByLowestPrice() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("price")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Product> sortByHighestPrice(boolean desc) {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(desc ? criteriaBuilder.desc(root.get("price")) : criteriaBuilder.asc(root.get("price")));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Product> hasDiscountPrice() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get("discountPrice"));
    }
}
