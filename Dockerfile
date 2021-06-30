FROM openjdk:13
COPY target/*.jar diplom-offers.jar
ENTRYPOINT java -jar diplom-offers.jar
EXPOSE 8080