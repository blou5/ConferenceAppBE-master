FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
WORKDIR /app
COPY target/conference-app.jar conference-app.jar
ENTRYPOINT ["java","-jar","/conference-app.jar"]