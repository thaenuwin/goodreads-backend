FROM maven:3.6.3-jdk-11-slim AS build
COPY ./ ./
RUN mvn clean package

COPY --from=build target/goodreads-backend-api.jar ./app.jar

USER app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]