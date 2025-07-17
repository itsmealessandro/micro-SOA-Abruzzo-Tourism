# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG AVAILIBALITY_LOCATION_PORT
RUN echo "AVAILIBALITY_LOCATION_PORT is: ${AVAILIBALITY_LOCATION_PORT:-NOT SET}"

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
