FROM openjdk:21-jdk-slim
VOLUME /tmp
ADD target/user-service.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]