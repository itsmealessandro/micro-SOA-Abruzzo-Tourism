# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG PROVIDER_WEATHER_PORT
RUN echo "PROVIDER_WEATHER_PORT is: ${PROVIDER_WEATHER_PORT:-NOT SET}"

# Imposto la cartella di lavoro
WORKDIR /build

# Copio solo la cartella con il progetto Maven
COPY app/provider-weather ./provider-weather

# Entro nella directory e compilo
WORKDIR /build/provider-weather

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copio il JAR dalla build
COPY --from=build /build/provider-weather/target/provider_weather-*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
