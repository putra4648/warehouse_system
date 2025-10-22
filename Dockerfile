# syntax=docker/dockerfile:1.4
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .

# Use BuildKit cache mount for Maven local repo to prefetch deps (speeds repeated builds & enables offline package)
RUN --mount=type=cache,target=/root/.m2 mvn -B -ntp dependency:go-offline

COPY src ./src

# Use the same cache and run the build in offline mode (-o) to avoid network calls during package
RUN --mount=type=cache,target=/root/.m2 mvn -o -B -ntp clean package -DskipTests

FROM eclipse-temurin:21 AS main
COPY --from=build /app/target/*.jar app.jar
EXPOSE 7000
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:7000/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]
