package com.gigantic.entity.Orders;

import com.gigantic.Mapper.IdBasedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "order_tracks")
@Table(name = "order_tracks")
public class OrderTrack extends IdBasedEntity {

    @Column(name = "notes", length = 256, nullable = false)
    private String notes;

    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Column(name = "updated_time", nullable = false)
    private Date updatedTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 45,nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //Constructor
    public OrderTrack() {
        //Default constructor
    }

    //Getter & Setter


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
