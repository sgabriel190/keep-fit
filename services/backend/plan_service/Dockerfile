FROM openjdk:8
WORKDIR ./app
COPY ./plan.db .
COPY ./target/plan_service-0.0.1-SNAPSHOT.jar .
EXPOSE 8080/tcp
CMD java -jar ./plan_service-0.0.1-SNAPSHOT.jar
