# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

# 1. Copia solo il POM per cache delle dipendenze
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copia i sorgenti
COPY src ./src

# 3. Pulisci eventuali residui e compila
RUN rm -rf /app/target && \
    mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]