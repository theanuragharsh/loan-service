#FROM openjdk:11-slim
#COPY target/loan-service-0.0.1-SNAPSHOT.jar loan-service-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/loan-service-0.0.1-SNAPSHOT.jar"]

# Use a base image with Java and Docker installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
#WORKDIR /app
ARG JAR_FILE=target/*.jar
# Copy the Spring Boot application JAR file to the container
COPY ${JAR_FILE} loan-service-v1.jar

# Expose port 8085 for the application
EXPOSE 8082

# Define the command to run the application
CMD ["java", "-jar", "loan-service-v1.jar"]

# build and run docker image
#1. cd /mnt/d/Bank\ Service/laon-service
#2. docker build -t loan-service .
#3. docker run -p 8082:8082 loan-service

#FROM adoptopenjdk:11-jdk-hotspot
#WORKDIR /app
#COPY . /app
#RUN ./mvnw package -DskipTests
#EXPOSE 8080
#CMD ["java", "-jar", "target/bank-service.jar"]