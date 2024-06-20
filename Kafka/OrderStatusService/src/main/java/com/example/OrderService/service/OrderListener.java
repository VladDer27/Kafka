package com.example.OrderService.service;

import com.example.OrderService.model.OrderStatusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderListener {
    @Autowired
    private KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    private static final String STATUS_TOPIC = "order-status-topic";

    @KafkaListener(topics = "order-topic", groupId = "order-status-service", containerFactory = "kafkaListenerContainerFactory")
    public void listenOrder() {
        OrderStatusEvent statusEvent = new OrderStatusEvent("CREATED", Instant.now());
        kafkaTemplate.send(STATUS_TOPIC, statusEvent);
    }
}


