package com.gigantic.shop.services.serviceImpl;

import com.gigantic.DTO.BrandDTO;
import com.gigantic.shop.config.BrandSpecificationConfig;
import com.gigantic.admin.Exception.BrandNotFoundException;
import com.gigantic.admin.Exception.DuplicateBrandException;
import com.gigantic.shop.repository.BrandRepository;
import com.gigantic.shop.services.BrandService;
import com.gigantic.category.repository.CategoryRepository;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository repo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Brand getById(Long id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new BrandNotFoundException("Could not find any brand with ID " + id));
    }

    //Problem with Recursive Query
//    @Override
//    public List<Brand> listAll(String name, String sortDirection, String sortField, String keyword) {
//        name = (name != null) ? name : "";
//        sortDirection = (sortDirection != null) ? sortDirection : "asc";
//        sortField = (sortField != null) ? sortField : "id";
//        keyword = (keyword != null) ? keyword : "";
//
//        // Build sort object
//        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
//
//        //Build Specifications
//        Specification<Brand> spec = Specification.where(null);
//        if (!name.isEmpty()) {
//            spec = spec.and(BrandSpecificationConfig.hasName(name));
//        }
//        if (!keyword.isEmpty()) {
//            spec = spec.and(BrandSpecificationConfig.containKeywords(keyword));
//        }
//        spec = spec.and(BrandSpecificationConfig.withCategories());
//
//        return repo.findAll(spec, sort);
//    }

    //Using DTO to avoid Recursive Query due join table with associated entities
    @Override
    public List<Brand> listAll(String name, String sortDirection, String sortField, String keyword) {
        name = (name != null) ? name : "";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";
        sortField = (sortField != null) ? sortField : "id";
        keyword = (keyword != null) ? keyword : "";

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        Specification<Brand> spec = Specification.where(null);
        if (!name.isEmpty()) {
            spec = spec.and(BrandSpecificationConfig.hasName(name));
        }
        if (!keyword.isEmpty()) {
            spec = spec.and(BrandSpecificationConfig.containKeywords(keyword));
        }
        spec = spec.and(BrandSpecificationConfig.withCategories());

        List<Brand> brands = repo.findAll(spec, sort);

        return repo.findAll(spec, sort);
    }


    @Override
    public Brand save(Brand brand) {
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
            Set<Set<Long>> categoryIds = Collections.singleton(repo.findById(id).get().getCategoryIds());
            existingBrand.setCategories(brand.getCategories());
            existingBrand.setCategoryIds(categoryIds);
        }

        existingBrand.setStatus(brand.isStatus());

        return repo.save(existingBrand);
    }


    @Override
    public BrandDTO toDTO(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        dto.setLogo(brand.getLogo());
        dto.setStatus(brand.isStatus());
        dto.setCategories(brand.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public Brand toEntity(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setId(dto.getId());
        brand.setName(dto.getName());
        brand.setLogo(dto.getLogo());
        brand.setStatus(dto.isStatus());
        brand.setCategories(dto.getCategories().stream()
                .map(categoryRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet()));
        return brand;
    }

    @Override
    public Brand deleteBrand(Brand brand) throws BrandNotFoundException {
        // Find the brand by ID
        Brand foundBrand = repo.findById(brand.getId()).orElse(null);

        if (foundBrand == null) {
            throw new BrandNotFoundException("Brand not found with ID: " + brand.getId());
        }

        // Delete the brand from the repository
        repo.delete(foundBrand);

        return foundBrand;
    }

}
