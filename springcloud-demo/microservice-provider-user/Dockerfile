FROM openjdk:8-jdk-alpine

ARG JAR_FILE
ADD ./target/${JAR_FILE} /var/run/microservice-provider-user.jar
RUN mkdir /etc/microservice-provider-user
EXPOSE 8000
VOLUME [ "/etc/microservice-provider-user" ]
CMD [ "java", "-jar", "/var/run/microservice-provider-user.jar", "--spring.config.location=file:/etc/microservice-provider-user/application.yml" ]
