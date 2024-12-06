package ru.iFellow;

import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BugPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.TaskPage;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.LoginPage.buttonCreate;
import static pages.ProjectPage.forAssertAllTasks;
import static pages.TaskPage.statusVal;
import static pages.TaskPage.statusVersion;

public class EduJiraTests {
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка логина и пароля")
    public void checkLogin() {
        // Шаг 1: Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.openPage().login("AT10", "Qwerty123");
        Assert.assertEquals($x(buttonCreate).getText(), "Создать");
    }

    @Test
    @DisplayName("Проверка перехода в Проект")
    public void checkProject() {
        // Шаг 1: Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.openPage().login("AT10", "Qwerty123");
        // Шаг 2: Переход в проект "Test"
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();
        Assert.assertEquals($x(forAssertAllTasks).getText(), "Все задачи");
    }

    @Test
    @DisplayName("Проверка количества задач")
    public void checkTask() {
        // Шаг 1: Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.openPage().login("AT10", "Qwerty123");

        // Шаг 2: Переход в проект "Test"
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();

        // Шаг 3: Проверка количества задач
        int initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask("Тестовая задача, удалить после прогона теста");
        int updatedTasks = projectPage.getTotalTasks();
        assertEquals(initialTasks + 1, updatedTasks, "Количество задач должно увеличиться на 1");
    }

    @Test
    @DisplayName("Проверка деталей задачи: Сделать и Version 2.0")
    public void CheckingTaskDetails() {
        // Шаг 1: Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.openPage().login("AT10", "Qwerty123");

        // Шаг 2: Переход в проект "Test"
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();

        // Шаг 3: Проверка количества задач
        int initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask("Тестовая задача, удалить после прогона теста");
        int updatedTasks = projectPage.getTotalTasks();
        assertEquals(initialTasks + 1, updatedTasks, "Количество задач должно увеличиться на 1");

        // Шаг 4: Проверка деталей задачи
        TaskPage taskPage = new TaskPage();
        taskPage.openTask("TestSeleniumATHomework")
                .verifyTaskDetails("Сделать", "Version 2.0");
        Assert.assertEquals($x(statusVal).getText(), "СДЕЛАТЬ");
        Assert.assertEquals($x(statusVersion).getText(), "Version 2.0");
    }

    @Test
    @DisplayName("Создание бага")
    public void checkBug() {
        // Шаг 1: Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.openPage().login("AT10", "Qwerty123");

        // Шаг 2: Переход в проект "Test"
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();

        // Шаг 3: Проверка количества задач
        int initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask("Тестовая задача, удалить после прогона теста");
        int updatedTasks = projectPage.getTotalTasks();
        assertEquals(initialTasks + 1, updatedTasks, "Количество задач должно увеличиться на 1");

        // Шаг 4: Проверка деталей задачи
        TaskPage taskPage = new TaskPage();
        taskPage.openTask("TestSeleniumATHomework")
                .verifyTaskDetails("Сделать", "Version 2.0");

        // Шаг 5: Создание нового бага и изменение статуса
        BugPage bugPage = new BugPage();
        projectPage.openProject();
        bugPage.createNewBug("Нет кнопки 'Оплатить'",
                "*_Шаги воспроизведения:_*\n" +
                        " # Открыть главную страницу сайта[http://marketsber.ru/]\n" +
                        "\n" +
                        "*_Фактический результат:* После добавления товара с корзину,не появляется кнопка 'Оплатить'*_Шаги воспроизведения:_*\n" +
                        "\n" +
                        " * Добавляем товар\n" +
                        " * Переходим в корзину\n" +
                        " * Наживаем оформить заказ\n" +
                        "\n" +
                        "*_Ожидаемый результат:_* Кнопка 'Оплатить' должны быть в нижнем правом углу",
                "ОС: MacOS Somnoma 14.6.1, Chrome 131.0.6778.70(arm64)");
    }
}
