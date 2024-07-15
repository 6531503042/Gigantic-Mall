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
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;

    public OrderServiceImpl(OrderRepository repo) {
        this.repo = repo;
    }

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
        Optional<Order> result = repo.findById(Long.valueOf(id));
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new OrderNotFoundException("Could not find any orders with ID " + id);
        }
    }

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            order.setOrderTime(new Date());
        }

        if (order.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }

        if (order.getPaymentMethod() == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PENDING);
        }

        Order existingOrder = repo.findById(order.getId()).orElse(null);

        if (existingOrder != null) {
            order.setId(existingOrder.getId());
        }

        order.setTotalPrice(order.getShippingPrice() + order.getProductPrice() + order.getTax());
        return repo.save(order);
    }

    @Override
    public Order updatedOrderStatus(Long id, OrderStatus status) throws OrderNotFoundException {
        Optional<Order> result = repo.findById(Long.valueOf(id));
        if (result.isPresent()) {
            Order order = result.get();
            order.setStatus(status);
            return repo.save(order);
        } else {
            throw new OrderNotFoundException("Could not find any orders with ID " + id);
        }
    }

    @Override
    public void delete(Long id) throws OrderNotFoundException {
        Optional<Order> result = repo.findById(Long.valueOf(id));
        if (result.isPresent()) {
            repo.deleteById(Long.valueOf(id));
        } else {
            throw new OrderNotFoundException("Could not find any orders with ID " + id);
        }
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

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

    @Override
    public OrderDTO toDTO(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

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

    @Override
    public void deleteUnpaidOrdersTask() {
        Date cutOffDate = new Date(System.currentTimeMillis() - 72 * 60 * 60 * 1000); // 72 Hours
        List<Order> orders = repo.findByPaymentStatus(cutOffDate, OrderStatus.UNPAID);
        for (Order order : orders) {
            repo.deleteById(order.getId());
        }
    }
}
