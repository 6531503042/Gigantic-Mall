package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Exception.DuplicateProductException;
import com.gigantic.admin.Exception.ProductNotFoundException;
import com.gigantic.admin.Repository.ProductRepository;
import com.gigantic.admin.Service.ProductService;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import com.gigantic.entity.Product.Product;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product getById(Long id) throws Exception {
        Product product = repo.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("Couldn't find any product with ID " + id);
        }
        return product;
    }

    @Override
    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setCreatedTime(new Date());
        }

        if (product.getAlias() == null || product.getAlias().isEmpty()) {
            String defaultAlias = product.getName().replaceAll(" ", "-").toLowerCase();
            product.setAlias(defaultAlias);
        } else {
            product.setAlias(product.getAlias().replaceAll(" ", "-").toLowerCase());
        }

        product.setUpdatedTime(new Date());
        return repo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product, Set<Category> categories, Set<Brand> brands) throws Exception {
        Product existingProduct = getById(id);

        if (product.getName() != null && !product.getName().equals(existingProduct.getName())) {
            Product duplicateProduct = repo.findByName(product.getName());
            if (duplicateProduct != null && !duplicateProduct.getId().equals(existingProduct.getId())) {
                throw new DuplicateProductException("Product with name " + product.getName() + " already exists");
            }
            existingProduct.setName(product.getName());
        }

        if (product.getAlias() != null && !product.getAlias().equals(existingProduct.getAlias())) {
            existingProduct.setAlias(product.getAlias());
        }

        if (product.getShortDescription() != null && !product.getShortDescription().equals(existingProduct.getShortDescription())) {
            existingProduct.setShortDescription(product.getShortDescription());
        }

        if (product.getFullDescription() != null && !product.getFullDescription().equals(existingProduct.getFullDescription())) {
            existingProduct.setFullDescription(product.getFullDescription());
        }

        if (product.getCreatedTime() != null && !product.getCreatedTime().equals(existingProduct.getCreatedTime())) {
            existingProduct.setCreatedTime(product.getCreatedTime());
        }

        if (product.getUpdatedTime() != null && !product.getUpdatedTime().equals(existingProduct.getUpdatedTime())) {
            existingProduct.setUpdatedTime(product.getUpdatedTime());
        }

        if (categories != null && !categories.equals(existingProduct.getCategories())) {
            existingProduct.setCategories(categories);
        }

        if (brands != null && !brands.equals(existingProduct.getBrands())) {
            existingProduct.setBrands(brands);
        }

        return repo.save(existingProduct);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Product updatedProductStatus(Long id, Boolean status) throws Exception {
        Product product = getById(id);
        product.setStatus(status);
        return repo.save(product);
    }
}
