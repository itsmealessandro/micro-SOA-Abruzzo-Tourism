FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG API_GATEWAY_INTERNAL_PORT
RUN echo "API_GATEWAY_INTERNAL_PORT is: ${API_GATEWAY_INTERNAL_PORT:-NOT SET}"
 
WORKDIR /gat_build

COPY  ./gateway/ ./gateway

WORKDIR /gat_build/gateway

RUN mvn clean package


# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR ./gateway/

COPY --from=build /gat_build/gateway/target/gateway-*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
