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

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private TestEntityManager entityManager;

//    @BeforeEach
//    public void setup() {
//        // Manually delete all category to clean up the database
//        entityManager.getEntityManager().createQuery("DELETE FROM Category ").executeUpdate();
//    }

    @Test
    public void testCreateRootCategory() {
        Category category = new Category("Furniture");
        Category savedCategory = repo.save(category);

        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

//    @Test
//    public void testCreateRootCategory() {
//        // Create a root category
//        Category rootCategory = new Category();
//        rootCategory.setName("Root Category");
//
//        repo.save(rootCategory);
//
//        // Fetch the root category from the database
//        Optional<Category> retrievedCategory = repo.findById(rootCategory.getId());
//        assertTrue(retrievedCategory.isPresent());
//        assertEquals("Root Category", retrievedCategory.get().getName());
//    }

    @Test
    public void testCreateSubCategory() {
        Category parent = new Category(6L);
        Category subCategory = new Category("Chair", parent);
        Category savedCategory = repo.save(subCategory);

        assertThat(savedCategory.getId()).isGreaterThan(0);
    }
}
