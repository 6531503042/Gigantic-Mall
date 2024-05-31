package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Exception.UserNotFoundException;
import com.gigantic.admin.Service.Impl.UserServiceImpl;
import com.gigantic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl services;

    //For RestController
//    @GetMapping("/list")
//    public List<User.java> getAllUser(){
//        return serviecs.getAllUser();
//    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = services.getAllUsers();
        return ResponseEntity.ok(users);
    }

//    @PostMapping("/add")
//    public ResponseEntity<User.java> createUser(@RequestBody User.java user) {
//        User.java savedUser = services.saveUser(user);
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

//    @GetMapping("/users/edit/{id}")
//    public String editUser(User user) {
//
//    }

    @PutMapping("/users/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = services.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            services.deleteById(id);
            return ResponseEntity.ok("User with id " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }




//    @PostMapping("/add")
//    public String addUser(@RequestBody User.java user) {
//        services.saveUser(user);
//        return "New user is added";
//    }

//    //For Controller
//    @GetMapping("/list")
//    public String listAll(Model model) {
//        List<User.java> users = serviecs.getAllUser();
//        model.addAttribute("users", users);
//        return "list";
//
//    }


}
