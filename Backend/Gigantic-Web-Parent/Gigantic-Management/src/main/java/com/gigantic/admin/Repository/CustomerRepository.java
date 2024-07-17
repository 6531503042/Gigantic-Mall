package com.gigantic.admin.Repository;

import com.gigantic.entity.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM customers c WHERE c.email = :email")
    Customer findByEmail(String email);


    @Query("SELECT c FROM customers c")
    Customer findAll(Specification<Customer> spec);
}
