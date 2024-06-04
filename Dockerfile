FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/clients-api-0.0.1-SNAPSHOT.jar client_api.jar
ENTRYPOINT ["java","-jar","/client_api.jar"]