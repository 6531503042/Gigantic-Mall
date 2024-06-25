package com.gigantic.admin.Controller;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.admin.Service.Impl.CustomerServiceImpl;
import com.gigantic.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
