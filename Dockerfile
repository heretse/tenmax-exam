FROM gradle:4.10.0-jdk8-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle assemble --no-daemon

FROM openjdk:8-jdk-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/tenmax-exam.jar

ENTRYPOINT ["java","-jar","/app/tenmax-exam.jar"]
