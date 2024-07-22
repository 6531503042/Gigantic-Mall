package com.gigantic.brand.repository;

import com.gigantic.entity.Brand;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {


    @Query("SELECT b FROM Brand b WHERE b.id = :id")
    Brand get(Long id);

    @Query("SELECT b FROM Brand b")
    List<Brand> findAll(Specification<Brand> spec, Sort sort);

    Long countById(Long id);

    boolean getByName(String name);

    @Query("SELECT b FROM Brand b WHERE b.name = :name")
    boolean existsByName(String name);

    @Query("SELECT b FROM Brand b WHERE b.name = :name")
    Brand findByName(String name);


    @Query("SELECT b FROM Brand b WHERE b.id = :id")
    Brand findCategoryIds(Long id);
}
