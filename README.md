
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
- **elasticsearch** - Port 9200 (REST API), Port 9300 (Transport)
- **logstash** - Port 5000 (TCP input), Port 9600 (Monitoring API)
- **kibana** - Port 5601
- **app** (Spring Boot backend) - Port 7000
- **web** (Nuxt frontend) - Port 3001

Access the services at:
- Frontend: http://localhost:3001
- Kibana (Log Visualization): http://localhost:5601
- Grafana (Metrics): http://localhost:3000
- Prometheus: http://localhost:9090

## ELK Stack (Elasticsearch, Logstash, Kibana)

The application includes a complete ELK stack for centralized logging and log analysis.

### Features

- **Elasticsearch**: Stores and indexes all application logs
- **Logstash**: Processes and transforms logs from the Spring Boot application
- **Kibana**: Provides a web interface for searching, visualizing, and analyzing logs

### Accessing Kibana

1. Start the application with `docker compose up -d`
2. Wait for all services to be healthy (especially Elasticsearch and Kibana)
3. Open Kibana at http://localhost:5601
4. Create an index pattern for `wms-logs-*` to view application logs
5. Use the Discover tab to search and filter logs

### Log Format

Application logs are sent to Logstash in JSON format with the following fields:
- `timestamp`: When the log was created
- `level`: Log level (DEBUG, INFO, WARN, ERROR)
- `logger_name`: The logger that created the log
- `message`: Log message
- `app`: Application name (wms)
- `environment`: Active Spring profile
- `thread_name`: Thread that generated the log
- `stack_trace`: Exception stack trace (if applicable)

### Configuration

Logstash configuration files are located in `config/elk/logstash/`:
- `logstash.conf`: Main pipeline configuration
- `logstash.yml`: Logstash settings
- `pipelines.yml`: Pipeline definitions

