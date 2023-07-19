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
FROM eclipse-temurin:17-alpine as runtimeJdk

# required for strip-debug to work
RUN apk add --no-cache binutils

# Build small JRE image
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /customjre

# Create final image
FROM alpine:latest

# setting up jdk
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# copying JRE
COPY --from=runtimeJdk /customjre $JAVA_HOME

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