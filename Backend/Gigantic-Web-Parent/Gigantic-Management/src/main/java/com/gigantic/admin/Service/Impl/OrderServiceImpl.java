package com.gigantic.admin.Service.Impl;

import com.gigantic.DTO.OrderDTO;
import com.gigantic.admin.Config.Specifications.OrderSpecificationsConfig;
import com.gigantic.admin.Exception.OrderNotFoundException;
import com.gigantic.admin.Repository.OrderRepository;
import com.gigantic.admin.Service.OrderService;
import com.gigantic.entity.Orders.Order;
import com.gigantic.entity.Orders.OrderStatus;
import com.gigantic.entity.Orders.PaymentMethods;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {

    //Injection of OrderRepository
    private final OrderRepository repo;

    public OrderServiceImpl(OrderRepository repo) {
        this.repo = repo;
    }

    //Service Logical
    @Override
    public List<Order> listAll(OrderStatus status, Long customerId, PaymentMethods paymentMethod, Date orderTime, float totalPrice, float shippingPrice, float productPrice, float tax) {
        Specification<Order> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and(OrderSpecificationsConfig.hasStatus(status));
        }

        if (customerId != null && customerId != 0) {
            spec = spec.and(OrderSpecificationsConfig.hasCustomerId(customerId));
        }

        if (paymentMethod != null) {
            spec = spec.and(OrderSpecificationsConfig.hasPaymentMethod(paymentMethod));
        }

        if (orderTime != null) {
            spec = spec.and(OrderSpecificationsConfig.hasOrderTime(orderTime));
        }

        if (totalPrice != 0) {
            spec = spec.and(OrderSpecificationsConfig.sortByHighestPrice(totalPrice));
        }

        if (shippingPrice != 0) {
            // Add specification for shipping price if required
        }

        if (productPrice != 0) {
            // Add specification for product price if required
        }

        if (tax != 0) {
            // Add specification for tax if required
        }

        return repo.findAll(spec);
    }



    @Override
    public Order get(Integer id) throws OrderNotFoundException {
        try {
            return repo.findById(Long.valueOf(id)).get();
        } catch (NoSuchElementException ex) {
            throw new OrderNotFoundException("Could not find any orders with ID " + id);
        }
    }

    @Override
    public Order save(Order order) {
        return repo.save(order);
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderTime(dto.getOrderTime());
        order.setShippingPrice(dto.getShippingPrice());
        order.setProductPrice(dto.getProductPrice());
        order.setTax(dto.getTax());
        order.setTotalPrice(dto.getTotalPrice());
        order.setStatus(dto.getStatus());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setCustomerId(dto.getCustomerId());
        return order;
    }

    public OrderDTO toDTO (Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setShippingPrice(order.getShippingPrice());
        dto.setProductPrice(order.getProductPrice());
        dto.setTax(order.getTax());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setCustomerId(order.getCustomerId());

        return dto;
    }

}
