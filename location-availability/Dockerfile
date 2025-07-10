FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-jammy
COPY --from=build /app/target/*.jar /app.jar
EXPOSE 8091
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8091/location-availability/actuator/health || exit 1
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]