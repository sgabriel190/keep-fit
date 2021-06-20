# Build stage
FROM openjdk:8
WORKDIR ./app
COPY . .
EXPOSE 8080/tcp
CMD ./gradlew bootRun
