#FROM eclipse-temurin:17-jdk-ubi9-minimal
FROM amazoncorretto:17-alpine

WORKDIR /app
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]