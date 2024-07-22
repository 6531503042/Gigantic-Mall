package com.gigantic.shop.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.gigantic.entity.Category;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "shop_categories")
public record ShopCategory(
        @Id Integer id,
        AggregateReference<Shop, Integer> shopId,
        AggregateReference<Category, Integer> categoryId
) {
}
