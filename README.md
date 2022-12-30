# srv-erp-sales
[![build](https://github.com/schambeck/srv-erp-sales/actions/workflows/gradle.yml/badge.svg)](https://github.com/schambeck/srv-erp-sales/actions/workflows/gradle.yml)
[![coverage](https://sonarcloud.io/api/project_badges/measure?project=schambeck_srv-erp-sales&metric=coverage)](https://sonarcloud.io/summary/new_code?id=schambeck_srv-erp-sales)

## ERP Sales API

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

    java -jar app/build/libs/srv-erp-sales-app-1.0.0.jar

### RabbitMQ Web Interface

    http://localhost:15672
    User: guest
    Pass: guest
