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

    @Scheduled(fixedRate = 360000) // Run every hour
    public void deleteUnpaidOrdersTask() {
        services.deleteUnpaidOrdersTask();
    }
}
