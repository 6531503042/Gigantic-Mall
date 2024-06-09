package com.gigantic.admin.Service.Impl;
import com.gigantic.admin.Config.BrandSpecificationConfig;
import com.gigantic.admin.Exception.BrandNotFoundException;
import com.gigantic.admin.Exception.DuplicateBrandException;
import com.gigantic.admin.Repository.BrandRepository;
import com.gigantic.admin.Service.BrandService;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl  implements BrandService {

    @Autowired
    private BrandRepository repo;

    @Override
    public Brand getById(Long id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new BrandNotFoundException("Could not find any brand with ID " + id));
    }

    @Override
    public List<Brand> listAll(String name, String sortDirection, String sortField, String keyword) {
        name = (name != null) ? name : "";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";
        sortField = (sortField != null) ? sortField : "id";
        keyword = (keyword != null) ? keyword : "";

        // Build sort object
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        //Build Specifications
        Specification<Brand> spec = Specification.where(null);
        if (!name.isEmpty()) {
            spec = spec.and(BrandSpecificationConfig.hasName(name));
        }
        if (!keyword.isEmpty()) {
            spec = spec.and(BrandSpecificationConfig.containKeywords(keyword));
        }
        return repo.findAll(spec, sort);
    }

    @Override
    public Brand save(Brand brand) throws Exception{
        return repo.save(brand);
    }

    @Override
    public Brand get(Long id) throws BrandNotFoundException {
        Brand brand = repo.findById(id).orElse(null);
        if (brand == null) {
            throw new BrandNotFoundException("Brand not found");
        }
        return brand;
    }

    @Override
    public void delete(Long id) throws BrandNotFoundException {
       Long countById = repo.countById(id);
        if (countById == null || countById == 0) {
            throw new BrandNotFoundException("Brand not found");
        }
        repo.deleteById(id);
    }

    @Override
    public Brand updatedBrandStatus(Long id, Boolean status) throws BrandNotFoundException {
        Brand brand = get(id);
        brand.setStatus(status);
        return repo.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand brand, Category category) throws Exception {
        Brand existingBrand = getById(id);

        if (brand.getName() != null && !brand.getName().equals(existingBrand.getName())) {
            Brand duplicateBrand = repo.findByName(brand.getName());
            if (duplicateBrand != null && !duplicateBrand.getId().equals(existingBrand.getId())) {
                throw new DuplicateBrandException("Brand already exists: " + brand.getName());
            }
            existingBrand.setName(brand.getName());
        }

        if (brand.getLogo() != null && !brand.getLogo().equals(existingBrand.getLogo())) {
            existingBrand.setLogo(brand.getLogo());
        }

        if (brand.getCategories() != null) {
            Set<Long> categoryIds = brand.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            existingBrand.setCategoryIds(categoryIds);
        }

        existingBrand.setStatus(brand.getStatus());

        return repo.save(existingBrand);
    }








}
