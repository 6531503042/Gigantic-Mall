package com.gigantic.entity.user;

import com.gigantic.admin.Repository.RoleRepository;
import com.gigantic.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void testCreateRole() {
        Role roleAdmin = new Role("Admin", "Can Manage everything");
        Role roleSales = new Role("Sales", "manage product price, "
                + "customers, shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories, brands, "
                + "products, articles and menus");
        Role roleShipper = new Role("Shipper", "view products, view orders "
                + "and update order status");
        Role roleAssistant = new Role("Assistant", "manage questions and reviews");

        roleRepository.saveAll(List.of(roleAdmin, roleSales, roleEditor, roleShipper, roleAssistant));
    }
}
