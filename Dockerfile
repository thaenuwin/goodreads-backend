FROM maven:3.6.3-jdk-11-slim AS build
COPY goodreads-backend /app/goodreads-backend
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jre-alpine AS goodreads-backend-api

RUN apk add libjpeg-turbo=1.5.3-r6 libtasn1=4.14-r0 musl=1.1.20-r6 libx11=1.6.12-r0 freetype=2.9.1-r3

ENV HOME=/opt/app/

RUN addgroup -g 1000 -S app && \
    adduser -u 1000 -S app -G app

RUN apk add tzdata && \
        cp /usr/share/zoneinfo/Asia/Singapore /etc/localtime && \
        echo "Asia/Singapore" > /etc/timezone

RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f

WORKDIR $HOME

COPY --from=build /usr/src/app/goodreads-backend-apiz/target/pids-cms-api.jar ./app.jar

RUN chown -R app:app $HOME
RUN chmod 750 $HOME

USER app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar", "--spring.config.location=file:/tmp/efs/fs1/java-config/cms/application.properties"]