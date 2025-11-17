# 1. Сборка jar внутри контейнера (builder-stage)
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# сначала копируем pom.xml (для кеширования зависимостей)
COPY pom.xml .
# потом исходники
COPY src ./src

# собираем jar
RUN mvn -q -DskipTests package

# 2. Лёгкий runtime-образ с только JRE и jar
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/liftlog-lite-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]