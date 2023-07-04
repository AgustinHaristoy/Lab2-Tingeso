FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} calculator-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/calculator-service-0.0.1-SNAPSHOT.jar"]