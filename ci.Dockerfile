# Stage 1: Build with Maven
FROM maven:3.8.3-adoptopenjdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml package

# Stage 2: Run with OpenJDK
FROM adoptopenjdk:11-jre-hotspot
COPY --from=build /usr/src/app/target/*.jar /app/
ENTRYPOINT ["java","-jar","/app/artist-0.0.1-SNAPSHOT.jar"]
