# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia il pom.xml
COPY pom.xml .

# Scarica le dipendenze
RUN mvn dependency:go-offline -B

# Copia il codice sorgente mantenendo la tua struttura
COPY java ./src/main/java
COPY resources ./src/main/resources

# Build dell'applicazione
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]