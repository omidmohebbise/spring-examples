# spring-mongo

This module runs a Spring Boot app backed by MongoDB.

## Start MongoDB with Docker Compose

From the `spring-mongo/` directory:

```bash
docker compose up -d
```

MongoDB will be available on `localhost:27017` and data will be persisted in the `mongo_data` Docker volume.

To stop:

```bash
docker compose down
```

To stop **and** delete data:

```bash
docker compose down -v
```

## Run the Spring app

From the `spring-mongo/` directory:

```bash
./gradlew bootRun
```

The app is configured in `src/main/resources/application.properties` to connect to:

- host: `localhost`
- port: `27017`
- database: `spring_mongo`

## Person API (example)

Base path: `/api/people`

### Create

```bash
curl -i -X POST http://localhost:8080/api/people \
  -H 'Content-Type: application/json' \
  -d '{"firstName":"Omid","lastName":"Mohebbi","age":30}'
```

### List all

```bash
curl -s http://localhost:8080/api/people | jq
```

### Filter by lastName

```bash
curl -s 'http://localhost:8080/api/people?lastName=Mohebbi' | jq
```

### Get by id

```bash
curl -s http://localhost:8080/api/people/{id} | jq
```

### Update

```bash
curl -i -X PUT http://localhost:8080/api/people/{id} \
  -H 'Content-Type: application/json' \
  -d '{"firstName":"Omid","lastName":"Mohebbi","age":31}'
```

### Delete

```bash
curl -i -X DELETE http://localhost:8080/api/people/{id}
```

## (Optional) Enable MongoDB auth

If you want to run MongoDB with authentication, you can change `docker-compose.yml` like this:

- add:
  - `MONGO_INITDB_ROOT_USERNAME: root`
  - `MONGO_INITDB_ROOT_PASSWORD: rootpassword`

Then switch Spring to a URI (example):

```properties
spring.data.mongodb.uri=mongodb://root:rootpassword@localhost:27017/spring_mongo?authSource=admin
```
