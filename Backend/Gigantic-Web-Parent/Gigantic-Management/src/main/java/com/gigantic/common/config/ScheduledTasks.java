package com.gigantic.common.config;

import com.gigantic.order.services.serviceImpl.OrderServiceImpl;
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
