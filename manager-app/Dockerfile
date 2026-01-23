# Этап 1: Сборка приложения
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Копируем parent POM и все модульные pom.xml
COPY pom.xml ./
COPY manager-app/pom.xml ./manager-app/
COPY catalogue-service/pom.xml ./catalogue-service/

# Копируем исходный код только для manager-app
COPY manager-app/src ./manager-app/src

# Собираем проект (зависимости загрузятся автоматически)
RUN mvn clean package -pl manager-app -am -DskipTests

# Этап 2: Запуск приложения
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Копируем собранный JAR из builder
COPY --from=builder /app/manager-app/target/*.jar app.jar

# Указываем профиль для работы с Docker
ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]