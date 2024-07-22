package com.gigantic.customer.controller;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.customer.repository.CustomerRepository;
import com.gigantic.customer.services.serviceImpl.CustomerServiceImpl;
import com.gigantic.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerServiceImpl service;
    private final CustomerRepository repo;

    public CustomerController(CustomerServiceImpl service, CustomerRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    //Logical Controller
    @GetMapping("/list")
    public List<Customer> getAllUser() {
        return service.getAllUsers();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Customer> get(Long id) throws CustomerNotFound {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        try {
            if (service.isEmailUnique(null,customer.getEmail())) {
                return ResponseEntity.ok(service.save(customer));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(Customer customer) {
        try {
            if (service.isEmailUnique(customer.getId(), customer.getEmail())) {
                return ResponseEntity.ok(service.save(customer));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<?> deleteByEmail(@PathVariable String email) {
        try {
            Optional<Customer> customerOptional = Optional.ofNullable(service.findByEmail(email));
            customerOptional.ifPresent(repo::delete);
            return customerOptional.map(customer -> ResponseEntity.ok().build())
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find any customer with email " + email));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find any customer with email " + email);
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find any customer with ID " + id);
        }
    }



}
