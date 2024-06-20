package com.gigantic.admin.Service;

import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import com.gigantic.entity.Product.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product getById(Long id) throws Exception;

    List<Product> listAll();

    Product save(Product product);

    Product updateProduct(Long id, Product product, Set<Category> categories, Set<Brand> brands) throws Exception;

    void delete(Long id);

    Product updatedProductStatus(Long id, Boolean status) throws Exception;
}
