package com.omidmohebbise.springboot4xfw7x.web;

import java.util.Map;

import com.omidmohebbise.springboot4xfw7x.messaging.OrderMessagingService;
import com.omidmohebbise.springboot4xfw7x.resilience.ResilienceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ResilienceService resilienceService;
    private final OrderMessagingService orderMessagingService;

    public ProductController(ResilienceService resilienceService, OrderMessagingService orderMessagingService) {
        this.resilienceService = resilienceService;
        this.orderMessagingService = orderMessagingService;
    }

    @GetMapping(path = "/{sku}", version = "1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getV1(@org.springframework.web.bind.annotation.PathVariable String sku) {
        return Map.of(
                "version", "v1",
                "sku", sku,
                "status", resilienceService.checkSku(sku)
        );
    }

    @GetMapping(path = "/{sku}", version = "2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getV2(@org.springframework.web.bind.annotation.PathVariable String sku) {
        return Map.of(
                "version", "v2",
                "sku", sku,
                "status", resilienceService.checkSku(sku),
                "message", "v2 endpoint resolved by native versioned request mapping"
        );
    }

    @GetMapping(path = "/orders/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> sendSampleOrder(@RequestBody(required = false) Map<String, Object> body) {
        String orderId = body != null && body.get("orderId") != null ? body.get("orderId").toString() : "ORD-100";
        orderMessagingService.send(orderId, "order-created");
        return Map.of("orderId", orderId, "result", "published via JmsClient");
    }
}

