package com.gigantic.entity.Product;

import com.gigantic.Mapper.IdBasedEntity;
import com.gigantic.entity.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends IdBasedEntity {

    @Column(unique = true, length = 255, nullable = false)
    private String name;

    @Column(length = 512, nullable = false, name = "short_description")
    private String shortDescription;

    @Column(length = 4096, nullable = false, name = "full_description")
    private String fullDescription;

    @Column(name = "created_time", nullable = false, updatable = false)
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    private boolean enabled;

    @Column(name = "in_stock")
    private boolean inStock;

    private float cost;

    private float price;

    @Column(name = "discount_percent")
    private float discountPercent;

    private float legnth;
    private float width;
    private float height;
    private float weight;

    private int reviewCount;
    private float averageRating;

    private String mainImage;

    //Join Table , Colunmn
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
