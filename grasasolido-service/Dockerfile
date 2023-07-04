FROM openjdk:19
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} grasasolido-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/grasasolido-service-0.0.1-SNAPSHOT.jar"]