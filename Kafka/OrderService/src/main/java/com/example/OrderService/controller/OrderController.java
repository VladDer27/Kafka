package com.example.OrderService.controller;


import com.example.OrderService.model.Order;
import com.example.OrderService.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        OrderEvent event = new OrderEvent(order.getProduct(), order.getQuantity());
        kafkaTemplate.send("order-topic", event);
    }
}

