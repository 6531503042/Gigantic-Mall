package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.CustomerNotFound;
import com.gigantic.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer get(Long id) throws CustomerNotFound;

    Customer save(Customer customer);

    boolean isEmailUnique(Long id, String email);

    void delete(Long id);
}
