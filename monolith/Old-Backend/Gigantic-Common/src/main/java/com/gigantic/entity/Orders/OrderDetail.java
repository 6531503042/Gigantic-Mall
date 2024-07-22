package com.gigantic.entity.Orders;

import com.gigantic.Mapper.IdBasedEntity;
import com.gigantic.entity.Product.Product;

import javax.persistence.*;

@Entity(name = "order_details")
@Table(name = "order_details")
public class OrderDetail extends IdBasedEntity {

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product_price", nullable = false)
    private float productPrice;

    @Column(name = "shipping_price", nullable = false)
    private float shippingPrice;

    @Column(name = "unit_price", nullable = false)
    private float unitPrice;

    @Column(name = "sub_total_price", nullable = false)
    private float subTotalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //Constructor
    public OrderDetail() {
        //Default constructor
    }

    //Getter & Setter
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(float subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
