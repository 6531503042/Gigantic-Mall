package com.gigantic.admin.Controller;

import com.gigantic.DTO.UserDTO;
import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Service.Impl.UserServiceImpl;
import com.gigantic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl services;

    //For RestController
//    @GetMapping("/list")
//    public List<User> getAllUser(){
//        return serviecs.getAllUser();
//    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> users = services.getAllUsers();
        return ResponseEntity.ok(users);
    }

//    @PostMapping("/add")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User savedUser = services.saveUser(user);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = services.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (DuplicateUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

//    @PostMapping("/add")
//    public String addUser(@RequestBody User user) {
//        services.saveUser(user);
//        return "New user is added";
//    }

//    //For Controller
//    @GetMapping("/list")
//    public String listAll(Model model) {
//        List<User> users = serviecs.getAllUser();
//        model.addAttribute("users", users);
//        return "list";
//
//    }


}
