package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.admin.Repository.CustomerRepository;
import com.gigantic.admin.Service.CustomerService;
import com.gigantic.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    //fields
    private final CustomerRepository repo;

    //Injection
    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    //Service Logical Code.

    @Override
    public Customer get(Long id) throws CustomerNotFound {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new CustomerNotFound("Couldn't find any customer with ID " + id);
        }
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setCreatedTime(new Date());
        } else {
            Customer existingCustomer = repo.findById(customer.getId()).get();
            customer.setCreatedTime(existingCustomer.getCreatedTime());
        }
        return repo.save(customer);
    }

    @Override
    public boolean isEmailUnique(String email) {
        Customer existingCustomer = repo.findByEmail(email);

        if (existingCustomer != null && existingCustomer.getId() != null) {
            return false;
        }

        return true;
    }

    @Override
    public void delete(Long id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
        } else {
            throw new NoSuchElementException("Couldn't find any customer with ID " + id);
        }
    }
}
