package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.BrandNotFoundException;
import com.gigantic.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> listAll(String name, String sortDirection, String sortField, String keyword);

    Brand save(Brand brand) throws Exception;

    Brand get(Long id) throws BrandNotFoundException;

    void delete(Long id) throws BrandNotFoundException;
}
