# RewardsCalculator (Spring Boot)

A Spring Boot application that calculates monthly and total reward points for customers based on their transactions over a 3-month period.

## Problem Statement

A retailer offers a rewards program:

- 2 points for every dollar spent **over $100**
- 1 point for every dollar spent **between $50 and $100**
- Example:  
  $120 purchase → 2×20 + 1×50 = **90 points**

The system must calculate:

- Reward points **per month**
- Reward points **total**
- For multiple customers
- No month hard-coded

---

## Features

- REST API for rewards calculation
- Reward points computed dynamically based on transaction dates
- No months hardcoded (fully dynamic)
- Multiple customers supported
- Proper exception handling
- Integration + Unit tests with JavaDocs
- Swagger documentation enabled
- Lombok applied across the project
- Production-ready code structure

---

## Architecture / Folder Structure

```
src/main/java
 └── com.example.rewards
      ├── controller
      ├── service
      ├── repository
      ├── dto
      ├── entity
      ├── exception
      ├── util
      └── config
src/test/java
 └── com.example.rewards
      ├── controller
      └── integration
src/main/resources
 ├── application.properties
 └── data.sql (optional)
.gitignore
pom.xml
README.md
```

---

## API Documentation – Swagger

Once the app is running:

### Swagger UI  
```
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI Spec  
```
http://localhost:8080/v3/api-docs
http://localhost:8080/v3/api-docs.yaml
```

---

## Endpoints

### **1. Get all customer rewards**
```
GET /api/rewards
```

### **2. Get rewards for a specific customer**
```
GET /api/rewards/{customerId}
```

---

## Running Tests

To run all unit + integration tests:

```
./mvnw test
```

---

## Generating JavaDocs

```
./mvnw javadoc:javadoc
```

JavaDocs output is generated under:

```
target/site/apidocs/
```
---

## Ignoring Build Output (Required)

This project contains a `.gitignore` file to ensure the following folders are **NOT checked into Git**:

- `target/`
- `bin/`

These contain generated build artifacts and must never be versioned.

---

## Run the Application

```
./mvnw spring-boot:run
```

Or run the main class from STS/IntelliJ:

```
RewardsApplication.java
```

---

## Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- springdoc-openapi (Swagger)
- JUnit 5 + MockMvc

---

## Packaging the application

To build the JAR:

```
./mvnw clean package
```

---

## Versioning

Use semantic versioning:

```
v1.0.0 - Stable release with rewards API, Swagger, tests, and JavaDocs
```

---

## Author

Developer: shekhar  
GitHub: https://github.com/Shekhar2791/RewardApp