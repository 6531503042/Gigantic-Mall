package com.gigantic.admin.Repository;

import com.gigantic.entity.Product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
