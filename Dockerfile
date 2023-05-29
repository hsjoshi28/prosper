# Use a Maven base image to build the application
FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project's pom.xml and build files to the container
COPY Prosper/pom.xml .
COPY Prosper/src ./src

# Build the application
RUN mvn clean package

# Use an OpenJDK base image for the runtime container
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime container
COPY --from=build /app/target/Prosper-0.0.1-SNAPSHOT.war

# Expose the default Spring Boot port
EXPOSE 8080

# Set the entrypoint command to run the Spring Boot application
CMD ["java", "-jar", "/app/target/Prosper-0.0.1-SNAPSHOT.war"]