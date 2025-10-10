
# Warehouse Management System

This is a side project to learn more about backend and frontend. It follows the principles of Clean Architecture and the CQRS pattern.


## Tech Stack

**Client:** Nuxt, Pinia

**Server:** Springboot


## Running with Docker Compose

To run the complete system (backend, frontend, database, and supporting services):

```bash
docker compose up -d
```

This will start the following services:
- **wms_db** (PostgreSQL) - Port 8000
- **keycloak** - Port 9000
- **grafana** - Port 3000
- **prometheus** - Port 9090
- **app** (Spring Boot backend) - Port 7000
- **web** (Nuxt frontend) - Port 3001

Access the frontend at: http://localhost:3001

