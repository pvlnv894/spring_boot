# Spring Boot Profiles 

Пример Spring Boot приложения с использованием профилей окружения и тестированием через Testcontainers.

Проект демонстрирует работу с различными конфигурациями приложения (dev / production) и переключением бинов через `@ConditionalOnProperty`.

---

## Возможности

- Spring Boot приложение
- Конфигурация через профили (dev / production)
- REST API контроллер
- Интеграционные тесты с использование Testcontainers для проверки разных окружений

---

## API

Получить текущий профиль: `GET /profile`

- Dev: `Current profile is dev`
- Production: `Current profile is production`

---

## Архитектура

Приложение использует интерфейс `SystemProfile` и две реализации:
- DevProfile
- ProductionProfile

Выбор реализации происходит через Spring конфигурацию:
`@ConditionalOnProperty(name = "netology.profile.dev")`

---

## Интеграционные тесты

Используются:

- JUnit 5
- Spring Boot Test
- Testcontainers

Проверяется:

- запуск dev контейнера
- запуск prod контейнера
- корректный ответ /profile для каждого окружения

---

## Запуск

Локально 
- `mvn spring-boot:run`
- или `java -jar target/app.jar`

