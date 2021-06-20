FROM openjdk:8
WORKDIR ./app
COPY ./build/libs/nutrition_service-0.0.1-SNAPSHOT.jar .
COPY ./images ./images
COPY ./nutrition.db .
EXPOSE 8080/tcp
CMD java -jar ./nutrition_service-0.0.1-SNAPSHOT.jar
