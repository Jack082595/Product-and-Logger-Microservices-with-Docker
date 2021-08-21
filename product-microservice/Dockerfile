FROM openjdk:8-jdk-alpine
LABEL maintainer="jackprosios"
VOLUME /tmp
ADD target/product-microservice-0.0.1-SNAPSHOT.jar product-microservice.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/product-microservice.jar"]