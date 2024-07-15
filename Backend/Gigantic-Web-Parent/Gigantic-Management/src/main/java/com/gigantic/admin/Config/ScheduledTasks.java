package com.gigantic.admin.Config;

import com.gigantic.admin.Service.Impl.OrderServiceImpl;
import com.gigantic.entity.Orders.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {

    private final OrderServiceImpl services;

    public ScheduledTasks(OrderServiceImpl services) {
        this.services = services;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Run every 24 hours
    public void deleteUnpaidOrdersTask() {
        services.deleteUnpaidOrdersTask();
    }
}
