package com.gigantic.entity.Product;

import com.gigantic.Mapper.IdBasedEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_details")
public class ProductDetail extends IdBasedEntity {

    @Column(nullable = false, length = 300)
    private String name;

    @Column(nullable = false, length = 300)
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //Constructor
    public ProductDetail() {
        //Default Constructor
    }

    public ProductDetail(String name, String value, Product product) {
        this.name = name;
        this.value = value;
        this.product = product;
    }

    public ProductDetail(Long id, String name, String value, Product product) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
        this.product = product;
    }

    //Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
