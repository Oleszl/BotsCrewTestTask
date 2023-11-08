FROM openjdk:17-alpine
COPY target/bots-crew-test-task-0.0.1-SNAPSHOT.jar university-app.jar
ENTRYPOINT ["java","-jar","university-app.jar"]