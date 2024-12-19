Feature: Проверка создания пользователя в Reqres API

  Scenario: Успешное создание пользователя
    Given базовый URL
    When отправлен POST запрос для создания пользователя с телом:
      """
      {
          "name": "Tomato",
          "job": "Eat maket"
      }
      """
    Then статус код должен быть 201
    And имя пользователя равно "Tomato"
    And работа пользователя равна "Eat maket"