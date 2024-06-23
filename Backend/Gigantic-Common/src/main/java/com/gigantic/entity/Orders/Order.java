package com.gigantic.entity.Orders;

import com.gigantic.Mapper.IdBasedEntity;
import com.gigantic.entity.Adress.AbstractAdress;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity(name = "orders")
@Table(name = "orders")
public class Order extends AbstractAdress {

    private String status;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;
}
