# 🏞️ Tourism & Outdoor in Abruzzo – Micro-SOA Architecture

This project implements a service-oriented architecture (SOA) for recommending, planning, and coordinating outdoor tourism activities in Abruzzo, Italy. It leverages REST, SOAP, microservices, and asynchronous interaction patterns.

---

## 🧱 Architecture Overview

The system is structured around the following key components:

- **Client App** – Web or mobile frontend for tourists.
- **External API Gateway** – Entry point for client interactions.
- **Prosumers**:
  - `Itinerary Aggregator`: builds personalized itineraries.
  - `Availability Coordinator`: verifies availability of the itinerary's resources.
- **Internal API Gateway** – Mediates prosumer-provider communication.
- **Providers**:
  - `Trails Provider`: trail and hiking info.
  - `Weather Provider`: weather/environmental data (SOAP).
  - `Events Provider`: local events, culture.
  - `Food Provider`: gastronomy and restaurants (optional).

All services are containerized and orchestrated using Docker Compose.

---

## 🚀 How to Run the Project

### 1. Prerequisites

- Docker
- Docker Compose (v3.9 or later)

### 2. Clone the Repository

```bash
git clone https://github.com/your-org/abruzzo-tourism-soa.git
cd abruzzo-tourism-soa
```

### 3. Build & Start the System

```bash
docker compose up --build
```

The following ports will be exposed:

| Service              | Port                           |
| -------------------- | ------------------------------ |
| External API Gateway | `8080`                         |
| Internal API Gateway | `8081`                         |
| Other services       | Internal only (Docker network) |

---

## 📌 Service Overview

### 🔁 Prosumers

- **Itinerary Aggregator**
  
  - Calls multiple providers to gather trails, events, and weather.
  - Composes and suggests tailored itineraries.

- **Availability Coordinator**
  
  - Asynchronously checks availability of trails, events, guides.
  - Synchronizes with the aggregator before returning results.

### 📡 Providers

- **provider-trails**: REST API for hiking and biking routes.
- **provider-weather**: SOAP service with forecasts and alerts.
- **provider-events**: REST API with regional events.
- **provider-food** *(optional)*: regional cuisine and restaurant listings.

---

## 🧭 Interaction Example

1. The client submits preferences to `api-gateway-external` at `/api/itinerary`.
2. `Itinerary Aggregator` queries multiple providers via `api-gateway-internal`.
3. `Availability Coordinator` checks if the itinerary is feasible.
4. Both prosumers synchronize, and the final plan is returned to the client.

---

## 📚 Documentation

- REST APIs are documented with **OpenAPI / Swagger**.
- SOAP service exposes a **WSDL** file.
- Architecture diagrams and sequence diagrams are available in `/docs`.

---

## 🐳 Docker Compose Setup

See `docker-compose.yml` for details. All services run on the same Docker bridge network (`soa-net`).

Example route configuration in the **internal gateway**:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: trails
          uri: http://provider-trails:8080
          predicates:
            - Path=/api/providers/trails/**
```

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot (REST + SOAP + Cloud Gateway)
- Apache CXF (SOAP)
- Docker, Docker Compose
- Swagger / OpenAPI
- Eureka (optional for service discovery)

---

## 📁 Project Structure

```
.
├── api-gateway-external/
├── api-gateway-internal/
├── itinerary-aggregator/
├── availability-coordinator/
├── provider-trails/
├── provider-weather/
├── provider-events/
├── provider-food/           # Optional
├── docker-compose.yml
└── README.md
```

---

## ✍️ Credits

- University of L'Aquila
- For the Service Oriented Engineering course – Final Test (2024–2025)
