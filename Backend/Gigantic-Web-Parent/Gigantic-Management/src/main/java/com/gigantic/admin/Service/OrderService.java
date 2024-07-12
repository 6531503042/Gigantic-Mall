package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.OrderNotFoundException;
import com.gigantic.entity.Orders.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order get(Integer id) throws OrderNotFoundException;

    Order save(Order order);
}
