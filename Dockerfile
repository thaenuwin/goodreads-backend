FROM openjdk:11

EXPOSE 8080

WORKDIR /applications

COPY target/goodreads-backend-api.jar /applications/application.jar

ENTRYPOINT ["java","-jar", "application.jar"]