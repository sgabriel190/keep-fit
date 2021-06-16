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
CMD ./gradlew bootRun
