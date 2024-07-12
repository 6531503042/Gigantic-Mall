package com.gigantic.DTO;

import com.gigantic.entity.Orders.OrderDetail;
import com.gigantic.entity.Orders.OrderStatus;
import com.gigantic.entity.Orders.PaymentMethods;

import java.util.Date;
import java.util.Set;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Date orderTime;
    private float shippingPrice;
    private float productPrice;
    private float tax;
    private float totalPrice;
    private OrderStatus status;
    private PaymentMethods paymentMethod;
    private Long customerId;
    private Set<OrderDetailDTO> orderDetails;
    private List<OrderTrackDTO> orderTracks;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderTrackDTO> getOrderTracks() {
        return orderTracks;
    }

    public void setOrderTracks(List<OrderTrackDTO> orderTracks) {
        this.orderTracks = orderTracks;
    }
}
