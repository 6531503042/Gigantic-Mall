package com.gigantic.admin.Service.Impl;
import com.gigantic.admin.Config.BrandSpecificationConfig;
import com.gigantic.admin.Exception.BrandNotFoundException;
import com.gigantic.admin.Repository.BrandRepository;
import com.gigantic.admin.Service.BrandService;
import com.gigantic.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl  implements BrandService {

    @Autowired
    private BrandRepository repo;

    @Override
    public List<Brand> listAll(String name, String sortDirection, String sortField, String keyword) {
        name = (name != null) ? name : "";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";
        sortField = (sortField != null) ? sortField : "id";

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







}
