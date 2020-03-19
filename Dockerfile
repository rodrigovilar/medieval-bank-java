#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

COPY pom.xml /tmp/

RUN mvn -DskipTests=true -B dependency:go-offline -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml verify
