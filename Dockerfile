# Building stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY . .
RUN mvn clean package -Dmaven.test.skip

# Env secrets github
ENV HEROKU_API_KEY=${HEROKU_API_KEY}
ENV HEROKU_APP_NAME=${HEROKU_APP_NAME}
ENV DATABASE_URL=${DATABASE_URL}

# Running stage
FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY --from=build /app/target/clients-api-0.0.1-SNAPSHOT.jar client_api.jar
ENTRYPOINT ["java","-jar","/client_api.jar"]