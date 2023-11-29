FROM maven:3.8.3-jdk-11-slim
RUN mvn clean build
EXPOSE 8080
ADD target/conference-app.jar conference-app.jar
ENTRYPOINT ["java","-jar","/conference-app.jar"]