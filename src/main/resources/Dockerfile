FROM openjdk:17-jdk-alpine
WORKDIR /app
RUN mkdir -p /app/processadordeimagem
VOLUME [" /app/processadordeimagem"]
COPY /target/processadordeimagem-0.0.1-SNAPSHOT.jar /app/processadordeimagem/processadordeimagem-api.jar
CMD ["java", "-jar", "/app/processadordeimagem/processadordeimagem-api.jar"]
