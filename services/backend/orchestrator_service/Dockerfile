FROM openjdk:8-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM openjdk:8-alpine
WORKDIR /app
COPY --from=build /app/build/libs/orchestrator_service-0.0.1-SNAPSHOT.jar .
CMD java -jar ./orchestrator_service-0.0.1-SNAPSHOT.jar
