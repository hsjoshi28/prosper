# Use an OpenJDK 11 base image
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot JAR file to the container
COPY target/Prosper-0.0.1-SNAPSHOT.war

# Expose the default Spring Boot port
EXPOSE 8080

# Set the entrypoint command to run the Spring Boot application
CMD ["java", "-jar", "Prosper-0.0.1-SNAPSHOT.war"]