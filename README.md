# Интернет Магазин на микросервисной архитектуре. 


### Реализация на Spring Boot несколько микросервисов.
Реализация на стеке REST + Angular технологий. В проекте использованы несколько баз данных в разных MS: MySQL, Postgres, Redis.
Магазин имеет единую точку входа на GateWay где настроена маршрутизация для MS, на фронте настроена маршрутизация для перемещения 
пользователей на JS. В магазине можно купить товар: Добавить его в корзину -> Cформировать заказ -> Оплатить заказ через PayPal, 
а на главной странице увидеть самые востребованные товары (Добавляемые в корзины пользователями).

#### MS Analityc Service (Сервис Аналитики)
Запрашивает данные у MS Core Service,  и сохраняет их в БД MySQL.  Данные запрашиваются каждые 10 секунд и содержат в себе 
названия продуктов, наиболее часто добавляемых в корзины покупателей. При загрузке стартовой страницы магазина,  данные 
показываются в виде списка востребованных товаров.

#### MS Auth Service (Сервис Авторизации пользователей)
Содержит в себе Модуль безопасности выдающий токены прошедшим авторизацию пользователям. Использует для своей работы Базу 
данных Postgres, в которой хранит информацию о пользователях магазина.

#### MS Cart Service (Сервис корзины пользователя)
Реализует функционал корзины пользователя, получает и сохраняет список товаров в объект корзины, затем сохраняет его в БД Redis.
В алгоритме корзины реализован перерасчет добавляемых одинаковых позиций товаров. При запросе из MS Core Service, отдает этот 
объект для формирования заказа.

#### MS Core Service (Сервис Ядро магазина)
Работает с товарами и их категориями, заказами пользователей и взаимодействием с платежной системой PayPal. 
В сервисе анстроена интеграция с корзиной пользователя (MS Cart Service), а так же с PayPal. Реализованн SOAP API для получения
списка товаров и списка категорий товаров. Общение межу сервисами настроено через Web Client. Данные о товарах и заказах хранятся в 
БД Postgres.

#### MS Front Service (Фронт магазина)
Работает с фронтом всех разделов магазина, маршрутизирует перемещение пользователя по его разделам (реализация на JS).

#### MS GateWay Service (BackEnd маршрутизация между MS магазина)
Настроена маршрутизация по MS магазина с проверкой валидности доступа.

## Для разворачивания окружжения через докер необходимо запустить файл: /flayway/start-migration.bat 
Он развернет несколько образов в Docker необходимых для рабоеты магазина (MySQL, Postgress, Redis), а так же запустит скрипты для миграции.




