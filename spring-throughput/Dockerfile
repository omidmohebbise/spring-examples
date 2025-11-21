# Stage 1: Build
FROM gradle:jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Stage 2: Run
FROM gradle:jdk21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]