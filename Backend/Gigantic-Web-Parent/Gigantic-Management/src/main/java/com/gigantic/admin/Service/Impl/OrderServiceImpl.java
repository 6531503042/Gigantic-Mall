package com.gigantic.admin.Service.Impl;

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

    public Order save(Order order) {
        return repo.save(order);
    }

}
