package com.gigantic.shop.dto;

import java.time.Instant;

public record ShopeDTO(
    Integer id,
    String name,
    String logo,
    String location,
    Instant activityDate,
    boolean status
) {
}
