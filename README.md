# Promotion service

Описание: сервис для управления тарифами и продвижением пользователей в системе. Позволяет создавать тарифы, покупать
продвижение, считать показы и синхронизировать данные с другими сервисами через Kafka.

Стек:

- Язык / платформа: Java + Spring Boot 3.5.4
- Асинхронность / интеграции: Apache Kafka, Avro, schema registry
- БД: PostgreSQL, hibernate, JPA + JDBC, миграции - Liquibase;
- Кеширование: Redis
- HTTP-клиенты: OpenFeign
- Утилиты: MapStruct, Lombok
- API: REST, OpenAPI/Swagger

Основное:

* school.faang.promotion_service.service.TariffServiceImpl:
    - Создание, удаление тарифов для продвижения пользователй
    - Публикует события в kafka для синхронизации тарифов в search_service
* school.faang.promotion_service.service.PromotionPurchaseServiceImpl:
    - Валидирует данные, создает событие на создание платежа
    - Обрабатывает успешный платеж, создает запись продвижение пользователя, публикует события на переиндексацию
      пользователя
* school.faang.promotion_service.service.PromotionCounterServiceImpl:
    - Отвечает за счетчик показов пользователя: инициализация, фактический показ пользователя в ленте, деактивацию
      продвижения
* src/main/java/school/faang/promotion_service/config - расположение конфигов kafka, redis, avro, swagger
* src/main/resources/avro/* расположение avro схем
* src/main/java/school/faang/promotion_service/client - расположение FeignClient
* src/main/java/school/faang/promotion_service/controller/ApiExceptionHandler.java - глобальный обработчик ошибок