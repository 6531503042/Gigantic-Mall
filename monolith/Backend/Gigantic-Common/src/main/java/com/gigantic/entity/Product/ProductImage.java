package com.gigantic.entity.Product;

import com.gigantic.Mapper.IdBasedEntity;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage extends IdBasedEntity {

    @Column(nullable = false, length = 300)
    private String name;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    //Constructor
    public ProductImage() {
        //Default Constructor
    }

    public ProductImage(String name, Product product) {
        this.name = name;
        this.product = product;
    }

    public ProductImage(Long id, String name, Product product) {
        super();
        this.id = id;
        this.name = name;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
