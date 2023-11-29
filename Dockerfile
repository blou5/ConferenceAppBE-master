FROM openjdk:11
WORKDIR /src
COPY target/conference-app.jar .
RUN mvn clean install

FROM openjdk:11
EXPOSE 8080
CMD ["java","-jar","/conference-app.jar"]

WORKDIR /app
COPY target/conference-app.jar conference-app.jar
ENTRYPOINT ["java","-jar","/conference-app.jar"]