# Spring Boot 4 + Spring Framework 7.x Feature Examples

This module demonstrates three newer Spring Framework 7.x capabilities in one minimal app:

- Built-in core resilience annotations (`@Retryable`, `@ConcurrencyLimit`)
- Native API versioning in request mappings (`version` attribute in mapping annotations)
- Fluent JMS messaging with `JmsClient`

## Feature Tour

- `com.omidmohebbise.springboot4xfw7x.resilience.InventoryClient`
  - Uses `@Retryable(maxAttempts = 3)` and `@ConcurrencyLimit(2)`.
- `com.omidmohebbise.springboot4xfw7x.web.ProductController`
  - Exposes the same path with two mapping versions:
    - `GET /api/products/{sku}?api-version=1`
    - `GET /api/products/{sku}?api-version=2`
- `com.omidmohebbise.springboot4xfw7x.messaging.OrderMessagingService`
  - Publishes with fluent `JmsClient` API to `orders.events`.

## Quick Try

```bash
./gradlew test
./gradlew bootRun
```

Then call:

```bash
curl 'http://localhost:8080/api/products/sku-1?api-version=1'
curl 'http://localhost:8080/api/products/sku-1?api-version=2'
```

