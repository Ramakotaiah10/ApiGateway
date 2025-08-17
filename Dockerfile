# ===========================
# Stage 1: Build with Maven
# ===========================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# ===========================
# Stage 2: Run the application
# ===========================
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render will respect $PORT)
EXPOSE 8080

# Set environment variables (Render will override)
ENV SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
