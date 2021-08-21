FROM openjdk:8-jdk-alpine
LABEL maintainer="jackprosios"
VOLUME /tmp
ADD target/logger-microservice-0.0.1-SNAPSHOT.jar logger-microservice.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/logger-microservice.jar"]