package com.gigantic.customer.services.serviceImpl;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.customer.repository.CustomerRepository;
import com.gigantic.customer.services.CustomerService;
import com.gigantic.entity.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gigantic.customer.config.CustomerSpecificationConfig;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
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
    public List<Customer> listAll(String keyword, String hasEmail) {

        keyword = (keyword != null) ? keyword : "";
        hasEmail = (hasEmail != null) ? hasEmail : "";

        Specification<Customer> spec = Specification.where(CustomerSpecificationConfig.hasKeyword(keyword))
                .and(CustomerSpecificationConfig.hasEmail(hasEmail));
        return (List<Customer>) repo.findAll(spec);
    }

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

    @Override
    public List<Customer> getAllUsers() {
        return (List<Customer>) repo.findAll();
    }
}
