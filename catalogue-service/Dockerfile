# Этап 1: Сборка приложения
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Копируем parent POM и все модульные pom.xml
COPY pom.xml ./
COPY catalogue-service/pom.xml ./catalogue-service/
COPY manager-app/pom.xml ./manager-app/

# Копируем исходный код только для catalogue-service
COPY catalogue-service/src ./catalogue-service/src

# Собираем проект (зависимости загрузятся автоматически)≠
RUN mvn clean package -pl catalogue-service -am -DskipTests

# Этап 2: Запуск приложения
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Копируем собранный JAR из builder
COPY --from=builder /app/catalogue-service/target/*.jar app.jar

# Указываем профиль для работы с Docker
ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]