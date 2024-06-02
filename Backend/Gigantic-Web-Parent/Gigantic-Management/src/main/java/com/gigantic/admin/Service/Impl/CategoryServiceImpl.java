package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.admin.Service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }



}
