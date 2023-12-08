FROM maven:3.8.3-jdk-11-slim AS build
WORKDIR /project
COPY pom.xml .
RUN mvn dependency:resolve
COPY src src
RUN mvn package
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/conference-app.jar"]

