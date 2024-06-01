package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Exception.UserNotFoundException;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.Impl.UserServiceImpl;
import com.gigantic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl services;
    @Autowired
    private UserRepository userRepository;

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

    @DeleteMapping("/users/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return Stream.of(id)
                .map(services::deleteById)
                .findFirst()
                .map(user -> ResponseEntity.ok("User with id " + id + " has been deleted"))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/users/deleteByEmail/{email}")
    public ResponseEntity<String> deleteByEmail(@PathVariable String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.getUserByEmail(email));
        userOptional.ifPresent(user -> {
            userRepository.delete(user);
        });
        return userOptional.map(user -> ResponseEntity.ok("User with email " + email + " has been deleted"))
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
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
