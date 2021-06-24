FROM openjdk:8 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM openjdk:8
WORKDIR /app
COPY --from=build /app .
CMD java -jar build/libs/orchestrator_service-0.0.1-SNAPSHOT.jar
