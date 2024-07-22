package com.gigantic.product.services;

import com.gigantic.DTO.ProductDTO;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import com.gigantic.entity.Product.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product getById(Long id) throws Exception;

    List<Product> listAll(String name, String sortDirection, String sortField, String keyword);

    Product save(Product product);

    Product updateProduct(Long id, Product product, Set<Category> categories, Set<Brand> brands) throws Exception;

    void delete(Long id);

    void saveProductPrice(Product product);

    Product updatedProductStatus(Long id, Boolean status) throws Exception;

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product product);
}
