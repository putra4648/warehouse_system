# Warehouse Management System (WMS)

A full-stack, comprehensive Warehouse Management System (WMS) built with Spring Boot, Vaadin, and PostgreSQL. It uses OAuth2 with Auth0 for authentication and provides full inventory management components with a modern, responsive Vaadin UI.

## Features

- **Inventory Tracking:** Full CRUD for tracking categories, products, suppliers, and items.
- **Warehouse Management:** Manage warehouses, zones, racks, locations, and bin numbers.
- **Stock Control:** Capabilities for inventory tracking, adjustments, inbound, and outbound management.
- **Authentication & Security:** Robust OAuth2 implementation via Auth0 and Okta Spring Boot Starter.
- **Modern UI:** Responsive Vaadin-based user interface with full CRUD operations.
- **Dockerized Infrastructure:** PostgreSQL database setup easily through Docker Compose.
- **Testing:** Spring Boot Testcontainers config for database integration tests.

## Technology Stack

### Backend

- Java 21
- Spring Boot 4.0.x
- Spring Security (OAuth2 / Okta Spring Boot Starter)
- Spring Data JPA
- Vaadin 25.2.x (Modern reactive web framework)
- PostgreSQL
- MapStruct & Lombok
- JUnit 5 & Testcontainers

### Frontend

- Vaadin Flow (Server-side UI framework)
- Responsive component library
- OAuth2 integration with Vaadin Security

### Infrastructure

- Docker & Docker Compose
- Auth0 (Identity Provider)

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/) and Docker Compose installed.
- [Java 21 Development Kit (JDK 21)](https://adoptium.net/).
- [Node.js](https://nodejs.org/) (Version 18+ recommended).
- pnpm (or npm/yarn/bun).

### Quickstart

#### 1. Start the Database

Run Docker Compose to start the WMS database:

```sh
docker compose up -d
```

#### 2. Run the Application

Start the Spring Boot application with Vaadin UI:

```sh
./mvnw spring-boot:run
```

The application will be available at `http://localhost:7000`.

## Directory Structure

- `src/main/java/id/putra/wms`: Spring Boot application containing Vaadin views, domain logic, config, and repositories.
- `src/main/frontend`: Vaadin frontend components and styling.
- `src/test`: Backend testing files and Testcontainer setups.
- `config/`: Contains standard or reference configurations.
- `docs/`: Documentation and module information.

## License

This project is licensed under the GNU General Public License v3.0 License - see the [LICENSE](LICENSE) file for details.
