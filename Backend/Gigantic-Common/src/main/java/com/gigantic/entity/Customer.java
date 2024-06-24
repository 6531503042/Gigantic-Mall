package com.gigantic.entity;

import com.gigantic.entity.Adress.AbstractAdress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "customers")
@Table(name = "customers")
public class Customer extends AbstractAdress {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private boolean enabled;

    @Column(name = "created_time", updatable = false)
    private Date createdTime;

    //Constructor
    public Customer() {
        //Default constructor
    }

    //Getter & Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
