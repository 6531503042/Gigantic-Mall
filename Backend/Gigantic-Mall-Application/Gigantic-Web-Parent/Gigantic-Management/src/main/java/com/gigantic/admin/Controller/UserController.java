package com.gigantic.admin.Controller;

import com.gigantic.admin.Service.RoleService;
import com.gigantic.admin.Service.UserService;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



//    @GetMapping("/users")
//    public String listAll(Model model) {
//        List<User> listUsers = userService.listAll();
//        model.addAttribute("listUsers", listUsers);
//        return "users";
//    }

    @GetMapping("/getAll")
    public List<User> listAll() {
        return (List<User>) userService.listAll();
    }

    @PostMapping("/add")
    public String addUser(Model model) {
        List<Role> listRoles = roleService.listAll();
        model.addAttribute("listRoles", listRoles);
        return "users";
    }

}
