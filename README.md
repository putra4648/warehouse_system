# Warehouse Management System (WMS)

A full-stack, comprehensive Warehouse Management System (WMS) built with Spring Boot, Nuxt 3, and PostgreSQL. It uses OAuth2 with Keycloak for authentication and provides full inventory management components.

## Features

- **Inventory Tracking:** Full CRUD for tracking categories, products, suppliers, and items.
- **Warehouse Management:** Manage warehouses, zones, racks, locations, and bin numbers.
- **Stock Control:** Capabilities for inventory tracking, adjustments, inbound, and outbound management.
- **Authentication & Security:** Robust OAuth2 implementation via Keycloak instance.
- **API Documentation:** Interactive Swagger UI and OpenAPI documentation for backend APIs.
- **Dockerized Infrastructure:** PostgreSQL database and Keycloak setup easily through Docker Compose.
- **Testing:** Spring Boot Testcontainers config for database integration tests.

## Technology Stack

### Backend

- Java 21
- Spring Boot 3.5.x
- Spring Security (OAuth2 Resource Server)
- Spring Data JPA
- PostgreSQL
- MapStruct & Lombok
- OpenAPI & Swagger
- JUnit 5 & Testcontainers

### Frontend (web/)

- Vue 3 & Nuxt 3
- Nuxt UI (Tailwind CSS, Headless UI)
- Nuxt Auth (Next Auth) for authentication
- Biome (Code Formatter)

### Infrastructure

- Docker & Docker Compose
- Keycloak

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/) and Docker Compose installed.
- [Java 21 Development Kit (JDK 21)](https://adoptium.net/).
- [Node.js](https://nodejs.org/) (Version 18+ recommended).
- pnpm (or npm/yarn/bun).

### Quickstart

#### 1. Start Infrastructure (PostgreSQL & Keycloak)

Run Docker Compose to start the WMS database and Keycloak services:

```sh
docker compose up -d
```

#### 2. Run the Backend

Start the Spring Boot API backend:

```sh
./mvnw spring-boot:run
```

The backend API runs on `http://localhost:7000`.

#### 3. Run the Frontend

In a new terminal window, navigate to the `web/` directory, install dependencies and start the Nuxt app:

```sh
cd web
pnpm install
pnpm dev
```

The frontend application will be available at `http://localhost:3000`.

## Usage & Authentication Guide

Please refer to the complete [GUIDE.md](./GUIDE.md) for details on how to set up Keycloak authentication, Postman REST API tokens, and access parameters.

## Directory Structure

- `src/main/java/id/putra/wms`: Spring Boot backend roots containing controllers, domain logic, config, and repositories.
- `src/test`: Backend testing files and Testcontainer setups.
- `web/`: Contains the complete frontend Nuxt 3 application code.
- `docker/`: Custom configurations and overrides for docker environments.
- `config/`: Contains standard or reference configurations.

## API Documentation

With the backend running locally, you can view the fully documented REST API via Swagger:

- Swagger Interactive UI: `http://localhost:7000/swagger-ui.html`
- OpenAPI JSON Spec: `http://localhost:7000/v3/api-docs`

To test authenticated endpoints in Swagger, see the instructions provided in [GUIDE.md](./GUIDE.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
