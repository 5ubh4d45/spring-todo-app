# Setting up builder
FROM gradle:jdk17-alpine AS builder

# set up working dir
WORKDIR /app

# copy settigns
COPY build.gradle.kts .
COPY settings.gradle.kts .

# copy src
COPY src src

# creates jar
RUN gradle bootJar

# Creates app runtime
FROM eclipse-temurin:17-alpine

LABEL authors="ixale.dev"

# Adding running port argument from build args
ARG PORT=8080

# adding the port arg into the docker runtime environment variable
ENV PORT=$PORT

WORKDIR /app

COPY --from=builder /app/build/libs/spring-todo-app-v0.1f.jar app.jar

RUN echo Running on Port: $PORT

EXPOSE $PORT

CMD java -jar app.jar --server.port=$PORT