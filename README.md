# QAIfellow_Kuznetsov

## 📖 Описание
Проект для автоматизации тестирования REST API на Java. 

---

## 🛠️ Технологии
- **Java 17**: язык программирования.
- **Maven**: управление зависимостями.
- **Cucumber**: написание тестов.
- **Rest-Assured**: работа с API.
- **Allure**: генерация отчетов.

---

## 📂 Структура проекта
- **src/main/resources/application.properties**: настройки проекта.
- **src/test/resources/features/**: описания тестов в формате Gherkin.
- **src/test/java/steps/**: реализация шагов для тестов.
- **src/test/resources/jsons/**: тестовые данные.

---

## 🚀 Запуск
1. Убедитесь, что установлены **Java 17** и **Maven**.
2. В терминале выполните:
   ```bash
   mvn clean test
 