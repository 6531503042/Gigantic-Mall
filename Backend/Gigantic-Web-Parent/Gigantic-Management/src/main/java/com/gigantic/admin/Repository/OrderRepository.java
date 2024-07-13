package com.gigantic.admin.Repository;

import com.gigantic.entity.Orders.Order;
import com.gigantic.entity.Orders.OrderStatus;
import com.gigantic.entity.Orders.PaymentMethods;
import com.gigantic.entity.Product.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("SELECT o FROM orders o")
    List<Order> findAll(Specification<Order> spec);
}
