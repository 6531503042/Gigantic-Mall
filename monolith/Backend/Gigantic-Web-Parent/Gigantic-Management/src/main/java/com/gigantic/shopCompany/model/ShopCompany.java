package com.gigantic.shopCompany.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "shop_companies")
public record ShopCompany(
        @Id Integer id,
        String name,
        String status) {
}
