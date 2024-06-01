package com.gigantic.admin.Controller;

import com.gigantic.admin.Config.Export.UserCSVExporter;
import com.gigantic.admin.Config.Export.UserExcelExporter;
import com.gigantic.admin.Config.FileUploadConfig;
import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Exception.UserNotFoundException;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.Impl.UserServiceImpl;
import com.gigantic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;

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

//    @GetMapping("/list")
//    public ResponseEntity<List<User>> getAllUser() {
//        List<User> users = services.getAllUsers();
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/users/list")
    public List<User> getAllUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        return services.getAllUsers(firstName, lastName, email, role, sortField, sortDirection);
    }

    @PostMapping("/users/add")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = services.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (DuplicateUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Creates a new user and uploads the user's photo.
     *
     * @param user         The user details.
     * @param multipartFile The user's photo.
     * @return A ResponseEntity with the created user or an error message.
     */
//    @PostMapping("/users/add")
//    public ResponseEntity<?> createUser(@RequestBody User user, @RequestParam("image") MultipartFile multipartFile) {
//        try {
//            // Save the user details first to get the user ID
//            User savedUser = services.saveUser(user);
//
//            // Check if the file is not empty
//            if (!multipartFile.isEmpty()) {
//                // Clean and get the filename
//                String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//                // Define the directory to upload the file using the user's ID
//                String uploadDir = "user-photos/" + savedUser.getId();
//
//                // Save the file to the specified directory
//                FileUploadConfig.saveFile(uploadDir, filename, multipartFile);
//            }
//
//            // Return the saved user details with a 201 Created status
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        } catch (DuplicateUserException e) {
//            // Return a 409 Conflict status if the user already exists
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            // Throw a runtime exception for any other errors
//            throw new RuntimeException(e);
//        }
//    }

    @PutMapping("/users/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = services.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<User> userOptional = Optional.ofNullable(services.getUserById(id));
        userOptional.ifPresent(user -> {
            services.deleteById(id);
        });
        return userOptional.map(user -> ResponseEntity.ok("User with id " + id + " has been deleted"))
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
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

    @GetMapping("/users/{id}/enabled/{status}")
    public ResponseEntity<String> updateUserEnabledStatus(@PathVariable Long id, @PathVariable boolean enabled) {
        try {
            services.updateUserEnabledStatus(id, enabled);
            String status = enabled ? "Enabled" : "Disabled";
            return ResponseEntity.ok("User with id " + id + " has been updated to " + status);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Export CSV & EXCEL & PDF
    @GetMapping("/users/export/csv")
    public void exportToCSV(HttpServletResponse response, String firstName, String lastName, String email, String role, String sortField, String sortDirection) throws IOException {
        try {
            List<User> listUsers = services.getAllUsers(firstName, lastName, email, role, sortField, sortDirection);

            UserCSVExporter exporter = new UserCSVExporter();
            exporter.export(listUsers, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response, String firstName, String lastName, String email, String role, String sortField, String sortDirection) throws IOException {
        try {
            List<User> listUsers = services.getAllUsers(firstName, lastName, email, role, sortField, sortDirection);

            UserExcelExporter exporter = new UserExcelExporter();
            exporter.export(listUsers, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
