package com.gigantic.admin.Repository;

import com.gigantic.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Long, Category> {
}
