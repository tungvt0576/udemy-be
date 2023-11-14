FROM openjdk:17-jdk-slim
EXPOSE 8087
ADD target/udemy-0.0.1-SNAPSHOT.jar udemy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar" , "/udemy-0.0.1-SNAPSHOT.jar"]
