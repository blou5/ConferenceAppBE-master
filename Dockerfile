FROM maven:3.8.3-jdk-11-slim AS build
RUN mkdir /project

WORKDIR /project

COPY target/conference-app.jar conference-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","project/conference-app.jar"]
