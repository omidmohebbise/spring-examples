package com.omidmohebbise.springboot4xfw7x.messaging;

import java.util.Map;

import org.springframework.jms.core.JmsClient;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class OrderMessagingService {

    private final JmsClient jmsClient;

    public OrderMessagingService(JmsClient jmsClient) {
        this.jmsClient = jmsClient;
    }

    public void send(String orderId, String status) {
        jmsClient.message()
                .destination("orders.events")
                .body(Map.of("orderId", orderId, "status", status))
                .send();
    }

    @JmsListener(destination = "orders.events")
    public void consume(Map<String, Object> payload) {
        // The listener demonstrates receiving messages sent by fluent JmsClient API.
        System.out.println("Order event received: " + payload);
    }
}

