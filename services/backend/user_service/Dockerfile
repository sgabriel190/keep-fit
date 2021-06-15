# Build stage
FROM alpine:3.13.5 AS build
WORKDIR ./app
COPY . .
RUN apk add openjdk8
RUN ./gradlew build

# Run stage
FROM alpine:3.13.5 
WORKDIR ./app
EXPOSE 8080/tcp
COPY --from=build /app .
RUN apk add openjdk8
CMD java -jar ./build/libs/user_service-0.0.1-SNAPSHOT.jar
