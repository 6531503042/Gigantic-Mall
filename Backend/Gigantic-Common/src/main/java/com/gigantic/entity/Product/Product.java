package com.gigantic.entity.Product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gigantic.Mapper.IdBasedEntity;
import com.gigantic.entity.Brand;
import com.gigantic.entity.Category;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends IdBasedEntity {

    @Column(unique = true, length = 300, nullable = false)
    private String name;

    @Column(unique = true, length = 200, nullable = false)
    private String alias;

    @Column(length = 512, nullable = false, name = "short_description")
    private String shortDescription;

    @Column(length = 4096, nullable = false, name = "full_description")
    private String fullDescription;

    @Column(name = "created_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @Column(name = "in_stock")
    private boolean inStock;

    @Column(name = "status")
    private boolean status;

    private float cost;
    private float price;

    @Column(name = "discount_percent")
    private float discountPercent;

    private float length;
    private float width;
    private float height;
    private float weight;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categories;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brands;

    //Constructor

    public Product() {
        super();
    }

    public Product(String name) {
        this.name = name;
    }

    //Getter & Setter


    //Calling from IdBasedEntity
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //etc.


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public Brand getBrands() {
        return brands;
    }

    public void setBrands(Brand brands) {
        this.brands = brands;
    }

    public Set<Category> getCategories() {
        return (Set<Category>) categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = (Category) categories;
    }

    public Set<Brand> getBrand() {
        return (Set<Brand>) brands;
    }

    public void setBrand(Set<Brand> brands) {
        this.brands = (Brand) brands;
    }
}