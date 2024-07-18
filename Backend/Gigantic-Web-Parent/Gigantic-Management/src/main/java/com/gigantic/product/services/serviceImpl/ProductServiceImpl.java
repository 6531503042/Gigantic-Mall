package com.gigantic.product.services.serviceImpl;

import com.gigantic.DTO.ProductDTO;
import com.gigantic.product.config.ProductSpecificationConfig;
import com.gigantic.admin.Exception.DuplicateProductException;
import com.gigantic.admin.Exception.ProductNotFoundException;
import com.gigantic.product.repository.ProductRepository;
import com.gigantic.product.services.ProductService;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import com.gigantic.entity.Product.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<Product> listAll(String name, String sortDirection, String sortField, String keyword) {
        name = (name != null) ? name : "";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";
        sortField = (sortField != null) ? sortField : "id";
        keyword = (keyword != null) ? keyword : "";

        Specification<Product> spec = Specification.where(ProductSpecificationConfig.hasName(name))
                .and(ProductSpecificationConfig.hasKeyword(keyword));

        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);

        return repo.findAll(spec, sort);
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

        Float length = product.getLength();
        Float someValue = 100.0f; // Example value, replace with actual logic
        if (length.equals(someValue)) {
            // Your logic here
        }

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

    /**
     * Saves the product price information.
     *
     * @param product the product to save the price for
     */
    @Override
    public void saveProductPrice(Product product) {
        // Find the product by ID or return null if not found
        Product existingProduct = repo.findById(product.getId()).orElse(null);

        // Set the cost, price, and discount percent of the existing product
        assert existingProduct != null;
        existingProduct.setCost(product.getCost());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscountPercent(product.getDiscountPercent());

        // Save the updated product
        repo.save(existingProduct);
    }

    @Override
    public Product updatedProductStatus(Long id, Boolean status) throws Exception {
        Product product = getById(id);
        product.setStatus(status);
        return repo.save(product);
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setAlias(dto.getAlias());
        product.setShortDescription(dto.getShortDescription());
        product.setFullDescription(dto.getFullDescription());
        product.setCreatedTime(dto.getCreatedTime());
        product.setUpdatedTime(dto.getUpdatedTime());
        product.setInStock(dto.getInStock());
        product.setStatus(dto.isStatus());
        product.setCost(dto.getCost());
        product.setPrice(dto.getPrice());
        product.setDiscountPercent(dto.getDiscountPercent());
        product.setLength(dto.getLength());
        product.setWidth(dto.getWidth());
        product.setHeight(dto.getHeight());
        product.setWeight(dto.getWeight());

        Set<Long> brandIds = dto.getBrandId();
        Set<Long> categoryIds = dto.getCategoryId();

        if (brandIds != null) {
            Set<Brand> brands = new HashSet<>();
            for (Long brandId : brandIds) {
                Brand brand = new Brand();
                brand.setId(brandId);
                brands.add(brand);
            }
            product.setBrands(brands);
        }

        if (categoryIds != null) {
            Set<Category> categories = new HashSet<>();
            for (Long categoryId : categoryIds) {
                Category category = new Category();
                category.setId(categoryId);
                categories.add(category);
            }
            product.setCategories(categories);
        }
        return product;
    }

    @Override
    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setAlias(product.getAlias());
        dto.setShortDescription(product.getShortDescription());
        dto.setFullDescription(product.getFullDescription());
        dto.setCreatedTime(product.getCreatedTime());
        dto.setUpdatedTime(product.getUpdatedTime());
        dto.setInStock(product.isInStock());
        dto.setStatus(product.isStatus());
        dto.setCost(product.getCost());
        dto.setPrice(product.getPrice());
        dto.setDiscountPercent(product.getDiscountPercent());
        dto.setLength(product.getLength());
        dto.setWidth(product.getWidth());
        dto.setHeight(product.getHeight());
        dto.setWeight(product.getWeight());
        dto.setBrandId(product.getBrands().stream().map(Brand::getId).collect(Collectors.toSet()));
        dto.setCategoryId(product.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
        return dto;
    }
}
