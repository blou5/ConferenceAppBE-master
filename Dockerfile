FROM openjdk:11
EXPOSE 8080
ADD target/conference-app.jar conference-app.jar
ENTRYPOINT ["java","-jar","conference-app.jar"]