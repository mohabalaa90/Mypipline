FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./target/java-maven-app-*.jar /root/Desktop/t/
WORKDIR /root/Desktop/t/

CMD java -jar java-maven-app-*.jar
