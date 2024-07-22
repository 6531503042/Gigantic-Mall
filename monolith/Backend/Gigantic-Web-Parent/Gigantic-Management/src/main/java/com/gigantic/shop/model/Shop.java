package com.gigantic.shop.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "shops")
public record Shop(
    @Id
    Integer id,
    String name,
    String logo,
    String location,
    Instant activityDate,
    boolean status
) {
}
