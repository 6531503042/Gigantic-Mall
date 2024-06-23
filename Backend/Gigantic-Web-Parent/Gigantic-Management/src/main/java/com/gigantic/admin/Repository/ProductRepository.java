package com.gigantic.admin.Repository;

import com.gigantic.entity.Product.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Long countById(Long id);

    @Query("SELECT p FROM Product p")
    Product findAll(Specification<Product> spec, Sort sort);
}
