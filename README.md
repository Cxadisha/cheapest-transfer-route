# cheapest-transfer-route
ZEROBYTE Internship Assignment

## Overview

This is a Spring Boot application that maximizes the total cost while ensuring the total weight of the transfers is less than or equal to `maxWeight`.

## Prerequisites

- Java 17
- Maven

## Build and Run Instructions

### Build the JAR

```sh
mvn clean package
```

### Run the Application

```sh
java -jar target/CheapestTransferRoute-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Health Check

#### Request:

```sh
curl -X GET http://localhost:8080/transfer/health-check
```

#### Response:

```json
"All Good"
```

### Calculate Optimal Transfer Route

#### Request Example:

```sh
curl -X POST http://localhost:8080/transfer/calculate \
     -H "Content-Type: application/json" \
     -d '{
         "maxWeight": 15,
         "availableTransfers": [
             {"weight": 5, "cost": 10},
             {"weight": 10, "cost": 20},
             {"weight": 3, "cost": 5},
             {"weight": 8, "cost": 15}
         ]
     }'
```

#### Response Example:

```json
{
    "selectedTransfers": [
        {"weight": 5, "cost": 10},
        {"weight": 10, "cost": 20}
    ],
    "totalCost": 30,
    "totalWeight": 15
}
```

## Running Tests

To run the test suite, use:

```sh
mvn test
```

## Dependencies

- `spring-boot-starter-web`: For building REST APIs
- `spring-boot-devtools`: Development tools (runtime only)
- `spring-boot-starter-test`: Testing utilities
- `junit-jupiter-api`: JUnit 5 support
- `mockito-core`: Mocking framework
- `rest-assured`: API testing

## Notes

- The application does not use a database.
- No environment variables are required.
- The API accepts JSON payloads and returns responses in JSON format.


