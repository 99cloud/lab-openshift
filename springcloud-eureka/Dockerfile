FROM openjdk:8-jdk-alpine

ARG JAR_FILE
RUN apk add curl jq
# ADD ./target/microservice-discovery-eureka-0.0.1-SNAPSHOT.jar /var/run/eureka-app.jar
ADD ./target/${JAR_FILE} /var/run/eureka-app.jar
EXPOSE 8761
CMD [ "java", "-jar", "/var/run/eureka-app.jar" ]
