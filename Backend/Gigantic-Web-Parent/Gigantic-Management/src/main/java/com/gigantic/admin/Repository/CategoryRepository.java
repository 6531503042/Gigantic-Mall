package com.gigantic.admin.Repository;

import com.gigantic.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category getParentCategory(Long id);

    Category findAllCategories();
}
