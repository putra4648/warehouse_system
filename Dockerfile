FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17 AS main
COPY --from=build /app/target/*.jar app.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "app.jar"]
