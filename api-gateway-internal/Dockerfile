FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY gateway/target/gateway-*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
