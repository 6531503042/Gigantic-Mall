package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.admin.Service.Impl.CustomerServiceImpl;
import com.gigantic.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerServiceImpl service;

    public CustomerController(CustomerServiceImpl service) {
        this.service = service;
    }

    //Logical Controller
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Customer> get(Long id) throws CustomerNotFound {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Customer> save(Customer customer) {
        try {
            if (service.isEmailUnique(customer.getEmail())) {
                return ResponseEntity.ok(service.save(customer));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Customer> update(Customer customer) {
        try {
            if (service.isEmailUnique(customer.getEmail())) {
                return ResponseEntity.ok(service.save(customer));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Customer> delete(Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
