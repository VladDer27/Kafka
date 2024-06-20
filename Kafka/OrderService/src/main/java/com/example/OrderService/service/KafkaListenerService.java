package com.example.OrderService.service;

import com.example.OrderService.model.OrderStatusEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "order-status-topic", groupId = "order-service", containerFactory = "kafkaListenerContainerFactory")
    public void listenOrderStatus(OrderStatusEvent message,
                                  @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                                  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) Integer partition,
                                  @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic,
                                  @Header(name = KafkaHeaders.RECEIVED_TIMESTAMP, required = false) Long timestamp) {
        System.out.println("Received message: " + message);
        System.out.println("Key: " + key + "; Partition: " + partition + "; Topic: " + topic + "; Timestamp: " + timestamp);
    }
}
