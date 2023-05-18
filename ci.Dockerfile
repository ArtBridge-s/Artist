# Stage 1: Build with Maven
FROM maven:3.8.3-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# Stage 2: Run with OpenJDK
FROM openjdk:17-jdk-alpine
COPY --from=build /usr/src/app/target/*.jar /app/
ENTRYPOINT ["java","-jar","/app/artist-0.0.1-SNAPSHOT.jar"]
