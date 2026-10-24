# Online Market

Модульный сервис для управления каталогом товаров интернет-магазина на базе Spring Boot и разделенный на два независимых приложения.

---

## Проект

- **`catalogue-service`** — REST API для управления каталогом товаров (создание, редактирование, получение списка и деталей товара). Защищен с помощью Spring Security.
- **`manager-app`** — веб-приложение для контент-менеджеров с интерфейсом на Thymeleaf. Взаимодействует с `catalogue-service` через `RestClient`, поддерживает аутентификацию пользователей по ролям (Spring Security) и хранение учетных записей в БД.
- **Миграции БД**: управление схемами баз данных обоих сервисов через Flyway.
- **Docker Compose**: оркестрация сервисов и баз данных для запуска в изолированном окружении.

---

## Стек технологий

- **Язык**: Java 17
- **Фреймворк**: Spring Boot - Spring MVC, Spring Data JPA, Spring Security
- **Шаблонизатор**: Thymeleaf
- **База данных**: PostgreSQL
- **Сборка**: Maven
- **Контейнеризация**: Docker, Docker Compose

---

## Структура проекта

```text
├── catalogue-service/      # REST API сервиса каталога товаров
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── manager-app/            # Веб-интерфейс администратора (UI на Thymeleaf)
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── docker-compose.yml      # Конфигурация для запуска сервисов и БД
└── pom.xml                 # Корневой POM мультимодульного проекта

```

---

## Запуск проекта

### Вариант 1: Запуск через Docker Compose (рекомендуется)

Сборка и подъем всех контейнеров одной командой:

```bash
docker compose up --build

```

После успешного запуска:

* **Веб-интерфейс менеджера**: `http://localhost:8080`
* **REST API каталога**: `http://localhost:8081`

### Вариант 2: Локальная сборка через Maven

1. Соберите проект:
```bash
./mvnw clean package

```


2. Для раздельного локального запуска каждого сервиса используйте профиль `standalone`:
```bash
# Запуск каталога
./mvnw spring-boot:run -pl catalogue-service -Dspring-boot.run.profiles=standalone

# Запуск панели менеджера
./mvnw spring-boot:run -pl manager-app -Dspring-boot.run.profiles=standalone

```

