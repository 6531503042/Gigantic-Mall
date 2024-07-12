package com.gigantic.admin.Service.Impl;

import com.gigantic.DTO.OrderDTO;
import com.gigantic.admin.Exception.OrderNotFoundException;
import com.gigantic.admin.Repository.OrderRepository;
import com.gigantic.admin.Service.OrderService;
import com.gigantic.entity.Orders.Order;
import org.springframework.stereotype.Service;

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
        if (dto.getOrderDetails() != null) {
            order.setOrderDetails(dto.getOrderDetails());
        }
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
