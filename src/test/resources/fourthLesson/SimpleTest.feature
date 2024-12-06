# language: ru

@afterSimpleTest
Функционал: Проверка Jira функционала

  Сценарий: Проверка логина
    Дано пользователь открывает страницу логина
    Когда пользователь вводит логин "AT10" и пароль "Qwerty123"
    Тогда на странице отображается кнопка "Создать"

  Сценарий: Переход в проект
    Дано пользователь авторизован с логином "AT10" и паролем "Qwerty123"
    Когда пользователь переходит в проект "Проекты"
    Тогда на странице отображается заголовок "Все задачи"

  Сценарий: Проверка количества задач
    Дано пользователь авторизован с логином "AT10" и паролем "Qwerty123"
    Когда пользователь переходит в проект "Проекты"
    Тогда на странице отображается заголовок "Все задачи"
    И пользователь создает новую задачу с описанием "Тестовая задача"
    Тогда количество задач увеличивается на 1

  Сценарий: Проверка деталей задачи
    Дано пользователь авторизован с логином "AT10" и паролем "Qwerty123"
    Когда пользователь переходит в проект "Проекты"
    И пользователь создает новую задачу с описанием "Тестовая задача"
    Тогда количество задач увеличивается на 1
    И детали задачи содержат "СДЕЛАТЬ" и "Version 2.0"

  Сценарий: Создание бага
    Дано пользователь авторизован с логином "AT10" и паролем "Qwerty123"
    Когда пользователь переходит в проект "Проекты"
    И пользователь создает новую задачу с описанием "Тестовая задача"
    И пользователь создает новый баг с описанием "Нет кнопки 'Оплатить'"