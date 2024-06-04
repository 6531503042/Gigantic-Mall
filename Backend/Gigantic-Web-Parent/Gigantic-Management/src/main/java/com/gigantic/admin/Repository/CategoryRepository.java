package com.gigantic.admin.Repository;

import com.gigantic.admin.Exception.CategoryNotFoundException;
import com.gigantic.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT c.parent FROM Category c WHERE c.id = :id")
    Category getParentCategory(Long id);

    @Query("SELECT c FROM Category c")
    List<Category> findAllCategories();

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Long countById(Long id);

    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<Category> findByParentIsNull();
}