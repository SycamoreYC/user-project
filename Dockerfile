# Dockerfile

FROM openjdk:11-jdk AS builder
MAINTAINER yechun.song
COPY . /source
WORKDIR /source
RUN ./gradlew clean build

FROM openjdk:11-jdk
COPY --from=builder /source/build/libs/user-project-0.0.1-SNAPSHOT.jar /project/
WORKDIR /project
EXPOSE 8080
CMD ["java", "-jar", "user-project-0.0.1-SNAPSHOT.jar"]
