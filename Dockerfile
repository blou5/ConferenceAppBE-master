FROM openjdk:11
EXPOSE 8080
ADD target/ConfluenceApp.jar ConfluenceApp.jar
ENTRYPOINT ["java" ,"-jar" ,"/ConfluenceApp.jar"]