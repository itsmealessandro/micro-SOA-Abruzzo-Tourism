FROM maven:3.9.9-eclipse-temurin-21 AS build

ARG CLIENT_APP_PORT
RUN echo "CLIENT_APP_PORT is: ${CLIENT_APP_PORT:-NOT SET}"
# Imposto la cartella di lavoro
WORKDIR /build

# Copio solo la cartella con il progetto Maven
COPY app/clientGuiApp ./clientGuiApp

# Entro nella directory e compilo
WORKDIR /build/clientGuiApp

RUN mvn clean package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copio il JAR dalla build
COPY --from=build /build/clientGuiApp/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
