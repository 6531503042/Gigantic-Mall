package com.gigantic.admin.Service;

import com.gigantic.DTO.OrderDTO;
import com.gigantic.admin.Exception.OrderNotFoundException;
import com.gigantic.entity.Orders.Order;
import com.gigantic.entity.Orders.OrderStatus;
import com.gigantic.entity.Orders.PaymentMethods;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {

    //Service Logical
    List<Order> listAll(OrderStatus status, Long customerId, PaymentMethods paymentMethod, Date orderTime, float totalPrice, float shippingPrice, float productPrice, float tax);

    Order get(Integer id) throws OrderNotFoundException;

    Order save(Order order);

    Order toEntity(OrderDTO dto);

    OrderDTO toDTO(Order order);
}
