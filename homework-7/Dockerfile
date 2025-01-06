# Use an official OpenJDK 21 base image (or the latest available version)
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled jar file into the container
COPY target/homework-7-0.0.1-SNAPSHOT.jar /app/homework-7.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "homework-7.jar"]
