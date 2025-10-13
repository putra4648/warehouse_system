# ELK Stack Setup Guide

## Overview

This guide describes the ELK (Elasticsearch, Logstash, Kibana) stack integration in the Warehouse Management System for centralized logging and log analysis.

## Architecture

```
Spring Boot Application (Port 7000)
         |
         | (TCP/JSON logs)
         v
    Logstash (Port 5000)
         |
         | (Index logs)
         v
   Elasticsearch (Port 9200)
         |
         | (Query logs)
         v
     Kibana (Port 5601)
```

## Components

### 1. Elasticsearch
- **Image**: `docker.elastic.co/elasticsearch/elasticsearch:8.11.1`
- **Ports**: 
  - 9200 (REST API)
  - 9300 (Transport)
- **Configuration**:
  - Single-node cluster
  - Security disabled for development
  - 512MB heap size

### 2. Logstash
- **Image**: `docker.elastic.co/logstash/logstash:8.11.1`
- **Ports**:
  - 5000 (TCP input for logs)
  - 9600 (Monitoring API)
- **Configuration Files**:
  - `config/elk/logstash/logstash.conf`: Main pipeline configuration
  - `config/elk/logstash/logstash.yml`: Logstash settings
  - `config/elk/logstash/pipelines.yml`: Pipeline definitions

### 3. Kibana
- **Image**: `docker.elastic.co/kibana/kibana:8.11.1`
- **Port**: 5601 (Web UI)
- **Features**:
  - Log search and filtering
  - Real-time log monitoring
  - Custom dashboards
  - Visualizations

### 4. Spring Boot Integration
- **Dependency**: `logstash-logback-encoder` version 8.0
- **Configuration**: `src/main/resources/logback-spring.xml`
- **Log Format**: JSON with structured fields

## Quick Start

### 1. Start All Services

```bash
docker compose up -d
```

Wait for all services to be healthy (about 2-3 minutes):

```bash
docker compose ps
```

### 2. Verify Services

Check Elasticsearch:
```bash
curl http://localhost:9200/_cluster/health
```

Check Logstash:
```bash
curl http://localhost:9600/_node/stats
```

Check Kibana:
```bash
curl http://localhost:5601/api/status
```

### 3. Access Kibana

1. Open browser and navigate to: http://localhost:5601
2. Wait for Kibana to initialize (first time may take a few minutes)

### 4. Create Index Pattern

1. In Kibana, go to **Management** → **Stack Management**
2. Click **Index Patterns** under Kibana
3. Click **Create index pattern**
4. Enter `wms-logs-*` as the index pattern
5. Select `@timestamp` as the time field
6. Click **Create index pattern**

### 5. View Logs

1. Go to **Analytics** → **Discover**
2. Select the `wms-logs-*` index pattern
3. You should see logs from the Spring Boot application

## Log Structure

Application logs contain the following fields:

| Field | Description | Example |
|-------|-------------|---------|
| `@timestamp` | Log timestamp | `2025-10-13T03:10:00.000Z` |
| `level` | Log level | `INFO`, `DEBUG`, `WARN`, `ERROR` |
| `logger_name` | Logger class | `id.putra.wms.WmsApplication` |
| `message` | Log message | `Application started` |
| `thread_name` | Thread name | `main` |
| `app` | Application name | `wms` |
| `environment` | Spring profile | `default`, `dev`, `prod` |
| `stack_trace` | Exception trace | (only for errors) |

## Common Operations

### Search Logs by Level

In Kibana Discover, use KQL (Kibana Query Language):

```
level: "ERROR"
level: "WARN" OR level: "ERROR"
```

### Search by Logger

```
logger_name: "id.putra.wms.*"
```

### Search by Time Range

Use the time picker in the top right corner of Kibana to select:
- Last 15 minutes
- Last 1 hour
- Last 24 hours
- Custom time range

### Filter by Application

```
app: "wms"
```

## Troubleshooting

### Elasticsearch Not Starting

Check logs:
```bash
docker compose logs elasticsearch
```

Common issues:
- Insufficient memory: Increase Docker memory limit
- Port conflict: Ensure ports 9200 and 9300 are available

### Logstash Connection Issues

Check logs:
```bash
docker compose logs logstash
```

Verify connectivity:
```bash
docker compose exec logstash curl http://elasticsearch:9200
```

### No Logs in Kibana

1. Check if Spring Boot app is running:
   ```bash
   docker compose logs app
   ```

2. Verify Logstash is receiving logs:
   ```bash
   docker compose logs logstash | grep "message"
   ```

3. Check Elasticsearch indices:
   ```bash
   curl http://localhost:9200/_cat/indices?v
   ```

4. Verify index pattern exists in Kibana

### Application Can't Connect to Logstash

Check environment variables:
```bash
docker compose exec app env | grep LOGSTASH
```

Should show:
```
LOGSTASH_HOST=logstash
LOGSTASH_PORT=5000
```

## Performance Tuning

### Adjust Heap Sizes

Edit `compose.yaml`:

For Elasticsearch:
```yaml
environment:
  - "ES_JAVA_OPTS=-Xms1g -Xmx1g"  # Increase to 1GB
```

For Logstash:
```yaml
environment:
  - "LS_JAVA_OPTS=-Xms512m -Xmx512m"  # Increase to 512MB
```

### Configure Log Retention

Create an Index Lifecycle Management (ILM) policy in Kibana to:
- Rotate indices daily
- Delete indices older than 30 days
- Optimize index size

## Production Recommendations

1. **Enable Security**:
   - Enable Elasticsearch security (xpack.security.enabled=true)
   - Configure TLS/SSL certificates
   - Set up user authentication

2. **Use Persistent Volumes**:
   ```yaml
   volumes:
     - elasticsearch-data:/usr/share/elasticsearch/data
   ```

3. **Configure Backup**:
   - Set up Elasticsearch snapshots
   - Regular backup schedule

4. **Monitor Performance**:
   - Use Elasticsearch monitoring
   - Set up alerts for disk space
   - Monitor heap usage

5. **Scale Resources**:
   - Increase heap size for production loads
   - Consider Elasticsearch cluster for high availability
   - Add more Logstash nodes for high throughput

## Additional Resources

- [Elasticsearch Documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Logstash Documentation](https://www.elastic.co/guide/en/logstash/current/index.html)
- [Kibana Documentation](https://www.elastic.co/guide/en/kibana/current/index.html)
- [Logstash Logback Encoder](https://github.com/logfellow/logstash-logback-encoder)
