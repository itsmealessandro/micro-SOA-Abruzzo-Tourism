# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /build

# Copy the availability-coordinator project folder
COPY app/availability-coordinator ./availability-coordinator

WORKDIR /build/availability-coordinator

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /build/availability-coordinator/target/availability-coordinator-*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
