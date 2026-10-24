FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace
COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package \
    && mkdir -p /app \
    && cp /tmp/blood-donation-management-system/blood-donation-api/blood-donation-api-1.0.0.jar /app/app.jar

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/app.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
