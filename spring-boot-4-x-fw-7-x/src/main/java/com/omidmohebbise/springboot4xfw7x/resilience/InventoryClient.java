package com.omidmohebbise.springboot4xfw7x.resilience;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.retry.annotation.ConcurrencyLimit;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class InventoryClient {

    private final AtomicInteger attempts = new AtomicInteger(0);

    @Retryable(maxAttempts = 3)
    @ConcurrencyLimit(2)
    public String fetchAvailability(String sku) {
        int currentAttempt = attempts.incrementAndGet();
        if (currentAttempt < 3) {
            throw new IllegalStateException("Temporary upstream issue for sku=" + sku + " attempt=" + currentAttempt);
        }
        return "Availability for %s confirmed on attempt %d".formatted(sku, currentAttempt);
    }

    public void resetAttempts() {
        attempts.set(0);
    }
}
