FROM openjdk:17-jdk-alpine
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demo-0.0.1.jar"]