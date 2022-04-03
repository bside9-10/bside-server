FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/study-1.0.jar bside-server.jar
ENTRYPOINT ["java", "-jar", "bside-server.jar"]