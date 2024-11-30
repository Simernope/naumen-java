FROM openjdk:21-jdk-slim

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN apt-get update && apt-get install -y maven && \
    mvn dependency:go-offline -B

RUN mvn clean package -DskipTests

RUN cp target/*.jar app.jar

ENV JAVA_TOOL_OPTIONS "-Djavafx.platform=gtk"

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
