FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY / ./
RUN mvn package

FROM eclipse-temurin:17 AS main
COPY --from=build /app /
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/application.jar"]