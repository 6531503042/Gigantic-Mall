package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Repository.OrderRepository;
import com.gigantic.admin.Service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    //Injection of OrderRepository
    private final OrderRepository repo;

    public OrderServiceImpl(OrderRepository repo) {
        this.repo = repo;
    }

    //Service Logical

}
