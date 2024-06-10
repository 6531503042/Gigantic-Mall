package com.gigantic.admin.Repository;

import com.gigantic.entity.Product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Long countById(Long id);
}
