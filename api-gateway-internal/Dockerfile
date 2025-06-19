FROM maven:3.9.9-eclipse-temurin-21 AS build
 
WORKDIR /gat_build

COPY  ./gateway/ ./gateway

WORKDIR /gat_build/gateway

RUN mvn clean package


# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR ./gateway/

COPY --from=build /gat_build/gateway/target/gateway-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
