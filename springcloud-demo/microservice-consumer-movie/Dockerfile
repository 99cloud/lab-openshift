FROM openjdk:8-jdk-alpine

ARG JAR_FILE
ADD ./target/${JAR_FILE} /var/run/microservice-consumer-movie.jar
RUN mkdir /etc/microservice-consumer-movie
EXPOSE 8010
VOLUME [ "/etc/microservice-consumer-movie" ]
CMD [ "java", "-jar", "/var/run/microservice-consumer-movie.jar", "--spring.config.location=file:/etc/microservice-consumer-movie/application.yml" ]
