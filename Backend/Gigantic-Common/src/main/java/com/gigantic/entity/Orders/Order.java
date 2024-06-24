package com.gigantic.entity.Orders;

import com.gigantic.Mapper.IdBasedEntity;
import com.gigantic.entity.Adress.AbstractAdress;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "orders")
@Table(name = "orders")
public class Order extends AbstractAdress {

    @Column(name = "order_time", nullable = false)
    private Date orderTime;

    @Column(name = "shipping_price", nullable = false)
    private float shippingPrice;

    @Column(name = "product_price", nullable = false)
    private float productPrice;

    @Column(name = "tax", nullable = false)
    private float tax;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    private String status;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;
}
