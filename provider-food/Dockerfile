# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Imposto la cartella di lavoro
WORKDIR /build

# Copio solo la cartella con il progetto Maven
COPY app/provider-food ./provider-food

# Entro nella directory e compilo
WORKDIR /build/provider-food

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copio il JAR dalla build
COPY --from=build /build/provider-food/target/provider_food-*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
