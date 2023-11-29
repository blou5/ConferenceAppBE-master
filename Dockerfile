FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY target/conference-app.jar conference-app.jar
CMD ["java","-jar","/conference-app.jar"]