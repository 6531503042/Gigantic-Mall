package com.gigantic.entity.user;

import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(true)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setup() {
        // Manually delete all users and roles to clean up the database
        entityManager.getEntityManager().createQuery("DELETE FROM User").executeUpdate();
    }

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1L);
        User userNamHM = new User("unique1@code.edu.az", "xaqani123", "Khagani", "Mammadli");
        userNamHM.addRole(roleAdmin);

        User savedUser = repo.save(userNamHM);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        Role roleAdmin = entityManager.find(Role.class, 1L);
        Role roleEditor = entityManager.find(Role.class, 3L);
        User userNamHM = new User("unique2@code.edu.az", "43534534534534", "soft", "kritsakorn");
        userNamHM.addRole(roleAdmin);
        userNamHM.addRole(roleEditor);

        User savedUser = repo.save(userNamHM);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testUpdateUserDetails() {
        User userNam = repo.findById(3L).orElseThrow(() -> new IllegalArgumentException("User.java not found"));
        userNam.setEnabled(true);
        userNam.setPassword("123456");
        userNam.setEmail("unique3@code.edu.az");
        repo.save(userNam);
    }

    @Test
    public void testUpdateUserRoles() {
        User userNam = repo.findById(3L).orElseThrow(() -> new IllegalArgumentException("User.java not found"));

        // Clear existing roles
        userNam.getRoles().clear();

        // Add new roles
        Role roleEditor = entityManager.find(Role.class, 3L);
        userNam.addRole(roleEditor);

        // Save the user
        repo.save(userNam);

        // Fetch the updated user to verify changes
        User updatedUser = repo.findById(3L).orElseThrow(() -> new IllegalArgumentException("User.java not found"));

        assertThat(updatedUser.getRoles().size()).isEqualTo(1);
        assertThat(updatedUser.hasRole("ROLE_EDITOR")).isTrue();
    }
}

