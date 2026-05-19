package com.omidmohebbise.springboot4xfw7x.resilience;

import org.springframework.stereotype.Service;

@Service
public class ResilienceService {

    private final InventoryClient inventoryClient;

    public ResilienceService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public String checkSku(String sku) {
        inventoryClient.resetAttempts();
        return inventoryClient.fetchAvailability(sku);
    }
}

