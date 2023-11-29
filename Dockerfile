FROM maven:3.8.3-jdk-11-slim AS build

WORKDIR /project

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /project/src

RUN mvn package

