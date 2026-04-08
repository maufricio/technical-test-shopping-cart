# 🧩 Microservices System - Shopping Cart
This project is a microservices-based system built with **Spring Boot**, designed to simulate an order management workflow including products, orders, and payments.

---

## 🚀 Architecture Overview

The system is composed of three independent microservices:

| Service         | Port  | Description |
|----------------|------|------------|
| **order-service**   | 8080 | Handles order creation and orchestration |
| **product-service** | 8081 | Provides product data (external API integration) |
| **payment-service** | 8082 | Simulates payment processing |

---

## 🔄 Flow

1. Client sends a request to **order-service**
2. order-service:
   - Validates the customer
   - Fetches product data from **product-service**
   - Calculates total
   - Calls **payment-service**
3. If payment is approved → order is completed
4. If payment fails → order is rejected

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- OpenFeign (for inter-service communication)
- H2 Database (in-memory)
- Lombok
- Spring AOP (logging)
- JUnit + Mockito (unit testing)

---

## 📦 Project Structure

Each microservice follows a clean architecture:

- `controllers` → REST endpoints  
- `services` → business logic  
- `repositories` → data access  
- `entities` → JPA entities  
- `dto` → request/response models  
- `client` → Feign clients for communication  
- `aspects` → logging with AOP  

---

## ▶️ How to Run

Run each microservice independently:

```bash
# order-service
cd order-service
mvn spring-boot:run

# product-service
cd product-service
mvn spring-boot:run

# payment-service
cd payment-service
mvn spring-boot:run
