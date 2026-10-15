```dockerfile
# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY . .

# Build the Spring Boot application
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run the application
CMD ["sh", "-c", "java -jar target/*.jar"]
```