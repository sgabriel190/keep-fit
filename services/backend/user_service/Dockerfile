FROM openjdk:8-alpine AS build
WORKDIR ./app
COPY . .
RUN ./gradlew build

FROM openjdk:8-alpine
COPY --from=build /app/build/libs/user_service-1.0.0.jar .
COPY --from=build /app/user.db .
EXPOSE 8080/tcp
CMD java -jar ./user_service-1.0.0.jar
