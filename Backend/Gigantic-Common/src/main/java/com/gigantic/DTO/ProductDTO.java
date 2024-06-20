package com.gigantic.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gigantic.Configs.FloatDeserializer;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class ProductDTO {

    private Long id;
    private String name;
    private String alias;
    private String shortDescription;
    private String fullDescription;
    private Date createdTime;
    private Date updatedTime;
    private boolean inStock;
    private boolean status;
    private float cost;

    @JsonDeserialize(using = FloatDeserializer.class)
    private float price;
    private float discountPercent;
    private float length;
    private float width;
    private float height;
    private float weight;

    private Set<Long> categoryId;
    private Set<Long> brandId;

    // Default constructor
    public ProductDTO() {}

    // Constructor with fields
    public ProductDTO(Long id, String name, String alias, String shortDescription, String fullDescription, Date createdTime, Date updatedTime, boolean inStock, boolean status, float cost, float price, float discountPercent, float length, float width, float height, float weight, Set<Long> categoryId, Set<Long> brandId) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.inStock = inStock;
        this.status = status;
        this.cost = cost;
        this.price = price;
        this.discountPercent = discountPercent;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    // Getters and setters

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

    public Set<Long> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Set<Long> categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getBrandId() {
        return brandId;
    }

    public void setBrandId(Set<Long> brandId) {
        this.brandId = brandId;
    }
}
