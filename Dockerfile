FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw \
    && ./mvnw clean package -DskipTests \
    && cp /tmp/blood-donation-management-system/blood-donation-api/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
