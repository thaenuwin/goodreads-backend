FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/src/app/goodreads-backend/src
COPY pom.xml /usr/src/app/goodreads-backend
RUN mvn -f /usr/src/app/goodreads-backend/pom.xml clean package

ENV HOME=/opt/app/

RUN addgroup -g 1000 -S app && \
    adduser -u 1000 -S app -G app


WORKDIR $HOME

COPY --from=build /usr/src/app/goodreads-backend-apiz/target/pids-cms-api.jar ./app.jar

RUN chown -R app:app $HOME
RUN chmod 750 $HOME

USER app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar", "--spring.config.location=file:/tmp/efs/fs1/java-config/cms/application.properties"]