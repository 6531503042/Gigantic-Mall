package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.BrandNotFoundException;
import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;

import java.util.List;

public interface BrandService {
    Brand getById(Long id) throws Exception;

    List<Brand> listAll(String name, String sortDirection, String sortField, String keyword);

    Brand save(Brand brand) throws Exception;

    Brand get(Long id) throws BrandNotFoundException;

    void delete(Long id) throws BrandNotFoundException;

    Brand updatedBrandStatus(Long id, Boolean status) throws BrandNotFoundException;

    Brand updatedBrand(Long id, Brand brand, Category category) throws Exception;
}
