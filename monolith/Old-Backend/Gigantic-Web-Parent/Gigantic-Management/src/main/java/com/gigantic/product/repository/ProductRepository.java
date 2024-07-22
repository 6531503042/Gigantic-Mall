package com.gigantic.product.repository;

import com.gigantic.entity.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Long countById(Long id);

    @Query("SELECT p FROM Product p")
    List<Product> findAll(Specification<Product> spec, Sort sort);

    @Query("SELECT p FROM Product p")
    Page<Product> searchProductsByName(String keyword, Pageable pageable);
}
