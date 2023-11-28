FROM openjdk:17-jdk-slim
EXPOSE 8087
ADD target/udemy-0.0.1-SNAPSHOT.jar udemy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar" ,"-Dspring.config.name=application-docker", "/udemy-0.0.1-SNAPSHOT.jar"]
