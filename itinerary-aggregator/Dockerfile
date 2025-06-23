# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /build

# Copy the itinerary-aggregator project folder
COPY app/itinerary-aggregator ./itinerary-aggregator

WORKDIR /build/itinerary-aggregator

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /build/itinerary-aggregator/target/itinerary-aggregator-*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
