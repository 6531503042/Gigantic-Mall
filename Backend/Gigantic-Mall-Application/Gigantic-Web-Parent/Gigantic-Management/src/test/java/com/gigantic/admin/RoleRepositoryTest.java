//package com.gigantic.admin;
//
//import com.gigantic.admin.Repository.RoleRepository;
//import com.gigantic.entity.Role;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//public class RoleRepositoryTest {
//
//    @Autowired
//    private RoleRepository repo;
//
//    @Test
//    public void testCreateFirstRole() {
//        Role roleAdmin = new Role("Admin", "Manage Everything");
//        Role savedRole = repo.save(roleAdmin);
//
//        assertThat(savedRole.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void testCreateRestRoles() {
//        Role roleSalesperson = new Role("Salesperson", "Manage Sales Operations");
//        Role roleEditor = new Role("Editor", "Manage content");
//        Role roleAssistant = new Role("Assistant", "Manage Users");
//
//
//    }
//}
