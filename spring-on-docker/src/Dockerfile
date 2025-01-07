FROM openjdk:21
LABEL authors="ljakutowicz"

COPY target/spring-on-docker-0.0.1-SNAPSHOT.jar spring-on-docker.jar
ENTRYPOINT ["java","-jar","/spring-on-docker.jar"]