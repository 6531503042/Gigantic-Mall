package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> listAll(String keyword, String hasEmail);

    Customer get(Long id) throws CustomerNotFound;

    Customer save(Customer customer);

    boolean isEmailUnique(Long id, String email);

    void encoderPassword(Customer customer);

    void delete(Long id);

    Customer findByEmail(String email);

    List<Customer> getAllUsers();
}
