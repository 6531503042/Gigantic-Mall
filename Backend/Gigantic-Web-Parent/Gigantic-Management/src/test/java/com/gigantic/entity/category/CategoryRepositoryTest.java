package com.gigantic.entity.category;

import com.gigantic.admin.Repository.CategoryRepository;
import com.gigantic.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setup() {
        // Manually delete all category to clean up the database
        entityManager.getEntityManager().createQuery("DELETE FROM Category ").executeUpdate();
    }

    @Test
    public void testCreateRootCategory() {
        Category category = new Category("Electronics");
        Category savedCategory = repo.save(category);

        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSubCategory() {
        Category parent = new Category(1L);
        Category subCategory = new Category("iPhone", parent);
        Category savedCategory = repo.save(subCategory);

        assertThat(savedCategory.getId()).isGreaterThan(0);
    }
}
