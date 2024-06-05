package com.gigantic.admin.Repository;

import com.gigantic.entity.Brand;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {


    @Query("SELECT b FROM Brand b WHERE b.id = :id")
    Brand get(Long id);

    List<Brand> findAll(Specification<Brand> spec, Sort sort);

    Long countById(Long id);
}
