# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG PROVIDER_TRAILS_PORT
RUN echo "PROVIDER_TRAILS_PORT is: ${PROVIDER_TRAILS_PORT:-NOT SET}"

# Imposto la cartella di lavoro
WORKDIR /build

# Copio solo la cartella con il progetto Maven
COPY app/provider-trails ./provider-trails

# Entro nella directory e compilo
WORKDIR /build/provider-trails

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copio il JAR dalla build
COPY --from=build /build/provider-trails/target/provider_trails-*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
