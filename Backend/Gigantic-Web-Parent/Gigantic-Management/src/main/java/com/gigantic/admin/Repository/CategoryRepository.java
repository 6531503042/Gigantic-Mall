package com.gigantic.admin.Repository;

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
}