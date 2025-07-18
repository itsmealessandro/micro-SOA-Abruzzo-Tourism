# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG PROVIDER_WEATHER_PORT
RUN echo "PROVIDER_WEATHER_PORT is: ${PROVIDER_WEATHER_PORT:-NOT SET}"

# Imposto la cartella di lavoro
WORKDIR /build

# Copia solo i file necessari per la compilazione
COPY app/provider-weather/pom.xml ./provider-weather/
COPY app/provider-weather/src ./provider-weather/src/

# Entro nella directory e compilo
WORKDIR /build/provider-weather

# Build con caching delle dipendenze
RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copio il JAR dalla build (con nome fisso)
COPY --from=build /build/provider-weather/target/provider-weather-*.jar ./app.jar


ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8082"]
