# Student Management System

A Spring Boot application for managing students with a Thymeleaf MVC UI, JPA persistence, and AI-powered student search.

## Features

- Student CRUD operations
- Thymeleaf-based web interface
- Bootstrap 5 styling
- H2 in-memory database for local development
- Automatic seed data in the `dev` profile
- AI-assisted student search using Google GenAI

## Tech Stack

- Java 21
- Spring Boot 4
- Spring MVC + Thymeleaf
- Spring Data JPA
- H2 Database
- Bootstrap 5
- Google GenAI / Spring AI

## Prerequisites

- Java 21+
- Maven or the included Maven wrapper
- A Google AI API key for the search feature

## Run the application

From the project root:

```bash
./mvnw spring-boot:run
```

The app starts on:

```text
http://localhost:8080
```

## Default configuration

The project is configured to run with the `dev` profile by default, so seed data is loaded automatically for local development.

## API key setup for search

To enable AI-powered search, set your Google GenAI API key in the application configuration or pass it as a command-line argument.

Example:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.ai.google.genai.api-key=YOUR_API_KEY"
```

You can also set the value in `src/main/resources/application.yml` under:

```yaml
spring:
  ai:
    google:
      genai:
        api-key: YOUR_API_KEY
```

## Database

The app uses an in-memory H2 database for development. Seed students are inserted automatically when the app starts in the `dev` profile.

## Project structure

- `src/main/java` — application source code
- `src/main/resources/templates` — Thymeleaf templates
- `src/main/resources/application.yml` — application configuration
- `src/main/java/com/afdzal/student_management_system/config/DataInitializer.java` — local seed data setup

## Notes

- The database is not intended for production use as configured.
- The AI search feature requires a valid API key.
- The default profile is `dev`, which keeps local demo data enabled without requiring extra command-line flags.
