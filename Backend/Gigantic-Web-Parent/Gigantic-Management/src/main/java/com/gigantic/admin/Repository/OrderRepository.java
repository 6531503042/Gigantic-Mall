package com.gigantic.admin.Repository;

import com.gigantic.entity.Orders.Order;
import com.gigantic.entity.Orders.OrderStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("SELECT o FROM orders o")
    List<Order> findAll(Specification<Order> spec);

    Order save(Order order);

    /**
     * Retrieves a list of orders that have not been paid for, have a specific order status, and were created before a given date.
     *
     * @param cutOffDate The date before which orders were created.
     * @param status The order status to filter by.
     * @return A list of orders that meet the specified criteria.
     */
    @Query("SELECT o FROM orders o WHERE o.paymentMethod IS NULL AND o.status = :status AND o.orderTime < :cutOffDate")
    List<Order> findByPaymentStatus(@Param("cutOffDate") Date cutOffDate, @Param("status") OrderStatus status);
}
