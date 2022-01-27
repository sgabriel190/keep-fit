FROM openjdk:8-alpine AS build
WORKDIR ./app
COPY . .
RUN ./mvnw package

FROM openjdk:8-alpine
WORKDIR ./app
COPY --from=build /app/target/plan_service-0.0.1-SNAPSHOT.jar .
COPY --from=build /app/plan.db .
EXPOSE 8080/tcp
CMD java -jar ./plan_service-0.0.1-SNAPSHOT.jar
