# api-erp-sales
[![build](https://github.com/schambeck/api-erp-sales/actions/workflows/gradle.yml/badge.svg)](https://github.com/schambeck/api-erp-sales/actions/workflows/gradle.yml)
[![coverage](https://sonarcloud.io/api/project_badges/measure?project=schambeck_api-erp-sales&metric=coverage)](https://sonarcloud.io/summary/new_code?id=schambeck_api-erp-sales)

## ERP Sales API

### Simple Clean Architecture

### Tech Stack

- Java 17
- Spring Boot
- PostgreSQL, Flyway
- RabbitMQ
- JUnit 5, Mockito, JaCoCo

### Start infra (PostgreSQL and RabbitMQ)

    docker-compose up -d

### Build artifact

    ./gradlew clean bootJar

### Run backend

    java -jar app/build/libs/api-erp-sales-app-1.0.0.jar

### RabbitMQ Web Interface

    http://localhost:15672
    User: guest
    Pass: guest

### Related Projects

- Supply: [erp-supply](https://github.com/schambeck/api-erp-supply)
