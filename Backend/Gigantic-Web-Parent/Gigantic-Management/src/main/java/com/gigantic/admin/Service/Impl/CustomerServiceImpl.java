package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    //fields
    private final CustomerRepository repo;

    //Injection
    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    //Service Logical Code.
}
