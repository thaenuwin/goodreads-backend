FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/src/app/goodreads-backend/src
COPY pom.xml /usr/src/app/goodreads-backend
RUN mvn -f /usr/src/app/goodreads-backend/pom.xml clean package

ENV HOME=/opt/app/


WORKDIR $HOME

COPY --from=build /usr/src/app/goodreads-backend-api/target/goodreads-backend-api.jar ./app.jar

RUN chown -R app:app $HOME
RUN chmod 750 $HOME

USER app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar", "--spring.config.location=file:/tmp/efs/fs1/java-config/cms/application.properties"]