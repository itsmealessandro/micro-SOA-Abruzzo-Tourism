# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG TRIP_PLANNER_PORT
RUN echo "TRIP_PLANNER_PORT is: ${TRIP_PLANNER_PORT:-NOT SET}"

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
