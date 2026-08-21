# MelAPI

REST API for managing a platform focused on honey products, developed as a study project to apply back-end concepts with **Java 21 and Spring Boot**.

## Overview

The project was designed using a layered architecture and includes resources for authentication, customers, categories, products, orders and product reviews.

Its main goal is to demonstrate practical knowledge of REST API development, persistence, validation, authentication, mapping between layers, documentation and containerization.

## Technologies

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Spring Security
- JWT
- Bean Validation
- MapStruct
- OpenAPI / Swagger
- Maven
- Docker
- Docker Compose

## Architecture

The application is organized into separate responsibilities:

```text
src/main/java/br/com/arthyxx
├── config
├── controllers
├── dto
├── enums
├── exceptions
├── mapper
├── models
├── repository
└── services
```

This structure keeps HTTP handling, business rules, persistence and data transfer concerns separated.

## Main features

- User authentication with JWT
- Customer management
- Category management
- Product management
- Order management
- Product reviews
- Data validation with Bean Validation
- Entity/DTO mapping with MapStruct
- Exception handling
- PostgreSQL persistence
- API documentation with OpenAPI / Swagger

## Running with Docker

### Requirements

- Docker
- Docker Compose

Clone the repository:

```bash
git clone https://github.com/Arthyxx/MelAPI-SpringBoot.git
cd MelAPI-SpringBoot
```

Start the application and PostgreSQL:

```bash
docker compose up --build
```

The API will be available at:

```text
http://localhost:8080
```

PostgreSQL is exposed on port `5432`.

To stop the containers:

```bash
docker compose down
```

## Authentication

The authentication controller exposes the login route:

```http
POST /api/auth/login
```

Authenticated routes use JWT-based security.

## Project purpose

This repository is part of my back-end development portfolio and was created to deepen my knowledge of Java, Spring Boot, REST APIs, application security, relational databases and containerized environments.

## Author

**Arthur Lima Gonçalves**

- GitHub: https://github.com/Arthyxx
- LinkedIn: https://www.linkedin.com/in/arthur-gonçalves-3957a4233/
