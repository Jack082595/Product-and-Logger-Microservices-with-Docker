FROM openjdk:8-jdk-alpine
LABEL maintainer="jackprosios"
VOLUME /tmp
ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/eureka-server.jar"]