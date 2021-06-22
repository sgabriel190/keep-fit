FROM openjdk:8 AS build
WORKDIR ./app
COPY . .
RUN ./gradlew build

FROM openjdk:8
WORKDIR ./app
COPY --from=build /app .
EXPOSE 8080/tcp
CMD java -jar ./build/libs/nutrition_service-0.0.1-SNAPSHOT.jar
