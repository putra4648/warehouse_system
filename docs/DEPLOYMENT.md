# Deployment Guide

## Deploy Workflow

This repository includes a `deploy.yml` GitHub Actions workflow for deploying the Warehouse Management System to a self-hosted runner.

### Prerequisites

1. **Self-hosted GitHub Runner**: Set up a self-hosted runner for your repository
2. **Docker & Docker Compose**: Installed on the self-hosted runner
3. **Java 21**: Required for building the application

### Workflow Triggers

The deployment workflow runs automatically:
- **After successful test completion**: Automatically triggered when the "Spring Boot Tests" workflow completes successfully on `master` or `main` branches
- **Manual trigger**: Via `workflow_dispatch` for manual deployments

> **Note**: The deployment will only proceed if the test workflow succeeded. This ensures that only tested code is deployed to the self-hosted runner.

### Environment Variables

The workflow uses the following environment variables that can be configured via GitHub Secrets:

| Variable | Description | Default Value |
|----------|-------------|---------------|
| `POSTGRES_USER` | PostgreSQL username | `admin` |
| `POSTGRES_PASSWORD` | PostgreSQL password | `admin123` |
| `POSTGRES_DB` | PostgreSQL database name | `wms_db` |
| `KC_HOSTNAME` | Keycloak hostname | `localhost` |
| `SPRING_DATASOURCE_URL` | Spring datasource URL | `jdbc:postgresql://wms_db:5432/wms_db` |
| `SPRING_DATASOURCE_USERNAME` | Spring datasource username | `admin` |
| `SPRING_DATASOURCE_PASSWORD` | Spring datasource password | `admin123` |
| `LOGSTASH_HOST` | Logstash hostname | `logstash` |
| `LOGSTASH_PORT` | Logstash port | `5000` |

### Setting up GitHub Secrets

To configure environment variables:

1. Go to your repository on GitHub
2. Navigate to **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret**
4. Add each secret with the appropriate name and value

### Workflow Steps

The deployment workflow performs the following steps:

1. **Checkout code** - Pulls the latest code from the repository
2. **Set up Java 21** - Configures the Java environment
3. **Build with Maven** - Compiles and packages the application
4. **Build Docker Image** - Creates a Docker image for the application
5. **Stop existing containers** - Safely stops any running containers
6. **Deploy with Docker Compose** - Starts all services using docker-compose
7. **Wait for services** - Gives services time to initialize
8. **Health check** - Verifies the application is running correctly

### Manual Deployment

To manually trigger the deployment:

1. Go to **Actions** tab in your repository
2. Select **Deploy to Self-Hosted Runner** workflow
3. Click **Run workflow**
4. Select the branch and click **Run workflow**

### Health Check

The workflow checks the application health at:
```
http://localhost:7000/actuator/health
```

If the application doesn't become healthy within 5 minutes (30 attempts × 10 seconds), the workflow will fail and display the application logs.

### Troubleshooting

If deployment fails:

1. Check the workflow logs in the Actions tab
2. View container logs: `docker compose logs app`
3. Check container status: `docker compose ps`
4. Verify environment variables are set correctly
5. Ensure the self-hosted runner has Docker and Java installed

### Local Testing

To test the deployment locally:

```bash
# Set environment variables (optional)
export KC_HOSTNAME=your-hostname

# Run docker compose
docker compose up -d

# Check health
curl http://localhost:7000/actuator/health

# Check Kibana
curl http://localhost:5601/api/status

# Check Elasticsearch
curl http://localhost:9200/_cluster/health
```

## ELK Stack Services

The deployment includes an ELK (Elasticsearch, Logstash, Kibana) stack for centralized logging:

- **Elasticsearch**: Port 9200 (REST API), Port 9300 (Transport)
- **Logstash**: Port 5000 (TCP input), Port 9600 (Monitoring API)
- **Kibana**: Port 5601 (Web UI)

### Accessing Logs

After deployment, you can access application logs through Kibana:

1. Navigate to http://localhost:5601
2. Create an index pattern for `wms-logs-*`
3. Use the Discover tab to search and analyze logs

### ELK Health Checks

To verify ELK stack is running:

```bash
# Check Elasticsearch cluster health
curl http://localhost:9200/_cluster/health

# Check Logstash node stats
curl http://localhost:9600/_node/stats

# Check Kibana status
curl http://localhost:5601/api/status
```
