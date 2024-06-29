package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.admin.Repository.CustomerRepository;
import com.gigantic.admin.Service.CustomerService;
import com.gigantic.entity.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    //fields
    private final CustomerRepository repo;
    private final PasswordEncoder passwordEncoder;

    //Injection
    public CustomerServiceImpl(CustomerRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
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
    public Customer save(@Valid Customer customer) {
        if (customer.getId() == null) {
            customer.setCreatedTime(new Date());
        } else {
            Customer existingCustomer = repo.findById(customer.getId()).get();
            customer.setCreatedTime(existingCustomer.getCreatedTime());
        }
        encoderPassword(customer);
        return repo.save(customer);
    }

    @Override
    public boolean isEmailUnique(Long id, String email) {
        Customer existingCustomer = repo.findByEmail(email);

        if (existingCustomer == null && id == null) return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (existingCustomer != null) return false;
        } else {
            if (existingCustomer.getId() != id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void encoderPassword(Customer customer) {
        if (customer.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }
    }

    @Override
    public void delete(Long id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
        } else {
            throw new NoSuchElementException("Couldn't find any customer with ID " + id);
        }
    }

    @Override
    public Customer findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
