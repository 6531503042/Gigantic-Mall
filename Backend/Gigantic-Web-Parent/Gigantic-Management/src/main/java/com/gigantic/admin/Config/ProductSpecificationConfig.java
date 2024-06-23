package com.gigantic.admin.Config;

import com.gigantic.entity.Product.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class ProductSpecificationConfig {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + keyword.toLowerCase() + "%")
                );
    }

    public static Specification<Product> sortByLowestPrice() {
        return (root, query, criteriaBuilder) ->
                (Predicate) criteriaBuilder.asc(root.get("price"));
    }

    public static Specification<Product> sortByHighestPrice(@SuppressWarnings("SameParameterValue") boolean desc) {
        return (root, query, criteriaBuilder) ->
                (Predicate) (desc ? criteriaBuilder.desc(root.get("price")) : criteriaBuilder.asc(root.get("price")));
    }

    public static Specification<Product> hasDiscountPrice() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get("discountPrice"));
    }


}
