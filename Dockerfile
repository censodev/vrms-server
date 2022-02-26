FROM gradle:jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/app/src
WORKDIR /home/app/src
RUN gradle bootJar

FROM openjdk:11-jre-slim
EXPOSE 8080
WORKDIR /
COPY --from=build /home/app/src/build/libs/vrms-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]