package com.gigantic.admin.Repository;

import com.gigantic.entity.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query("SELECT c.parent FROM Category c WHERE c.id = :id")
    Category getParentCategory(Long id);

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Long countById(Long id);

    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<Category> findByParentIsNull();

    @Query("UPDATE Category c SET c.enabled = ?2 WHERE c.id = ?1")
    @Modifying
    Category updatedEnabledStatus(Long id, boolean enabled);

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findByName(String name);

    Category getById(Long id);
}