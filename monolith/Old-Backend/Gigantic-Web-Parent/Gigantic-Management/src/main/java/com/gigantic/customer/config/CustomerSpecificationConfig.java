package com.gigantic.customer.config;

import com.gigantic.entity.AuthenticationType;
import com.gigantic.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecificationConfig {

    // Search By Email
    public static Specification<Customer> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    // Search By keyword like firstName , lastName, email
    public static Specification<Customer> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + keyword.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + keyword.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + keyword.toLowerCase() + "%")
        );
    }

    // Search By Authentication
    public static Specification<Customer> Authentication(AuthenticationType authenticationType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("authenticationType"), authenticationType);
    }



}
