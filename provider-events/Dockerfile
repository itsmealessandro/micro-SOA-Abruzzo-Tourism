# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG PROVIDER_EVENTS_PORT
RUN echo "PROVIDER_EVENTS_PORT is: ${PROVIDER_EVENTS_PORT:-NOT SET}"

# Imposto la cartella di lavoro
WORKDIR /build

# Copio solo la cartella con il progetto Maven
COPY app/provider-events ./provider-events

# Entro nella directory e compilo
WORKDIR /build/provider-events

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copio il JAR dalla build
COPY --from=build /build/provider-events/target/provider-events-*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
