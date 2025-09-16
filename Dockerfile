FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
#COPY package.json watch.js ./
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21 AS main
COPY --from=build /app/target/*.jar app.jar
EXPOSE 7000
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:7000/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]
