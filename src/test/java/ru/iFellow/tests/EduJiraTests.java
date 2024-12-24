package ru.iFellow.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.iFellow.hooks.Hooks;
import ru.iFellow.pages.BugPage;
import ru.iFellow.pages.LoginPage;
import ru.iFellow.pages.ProjectPage;
import ru.iFellow.pages.TaskPage;
import io.qameta.allure.*;
import ru.iFellow.testWatcher.TestListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestListener.class)
@Epic("Проверка функциональности веб-приложения Jira")
public class EduJiraTests extends Hooks {

    @Test
    @DisplayName("Проверка логина и пароля")
    public void checkLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.buttonCreate("Создать");
    }

    @Test
    @DisplayName("Проверка перехода в Проект")
    public void checkProject() {
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();
        projectPage.assertAllTasksText("Все задачи");
    }

    @Test
    @DisplayName("Проверка количества задач")
    public void checkTask() {
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();
        projectPage.assertAllTasksText("Все задачи");

        int initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask("Тестовая задача, удалить после прогона теста");
        int updatedTasks = projectPage.getTotalTasks();
        assertEquals(initialTasks + 1, updatedTasks, "Количество задач должно увеличиться на 1");
    }

    @Test
    @DisplayName("Проверка деталей задачи: Сделать и Version 2.0")
    public void CheckingTaskDetails() {
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();

        int initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask("Тестовая задача, удалить после прогона теста");
        int updatedTasks = projectPage.getTotalTasks();
        assertEquals(initialTasks + 1, updatedTasks, "Количество задач должно увеличиться на 1");

        TaskPage taskPage = new TaskPage();
        taskPage.openTask("TestSeleniumATHomework")
                .verifyTaskDetails("Сделать", "Version 2.0");
    }

    @Test
    @DisplayName("Создание бага")
    public void checkBug() {
        ProjectPage projectPage = new ProjectPage();
        projectPage.openProject();

        int initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask("Тестовая задача, удалить после прогона теста");
        int updatedTasks = projectPage.getTotalTasks();
        assertEquals(initialTasks + 1, updatedTasks, "Количество задач должно увеличиться на 1");

        TaskPage taskPage = new TaskPage();
        taskPage.openTask("TestSeleniumATHomework")
                .verifyTaskDetails("Сделать", "Version 2.0");

        BugPage bugPage = new BugPage();
        projectPage.openProject();
        bugPage.createNewBug("Нет кнопки 'Оплатить'",
                """
                        *_Шаги воспроизведения:_*
                        # Открыть главную страницу сайта [http://marketsber.ru/]
                            
                        *_Фактический результат:_* После добавления товара в корзину, не появляется кнопка 'Оплатить'
                            
                        *_Шаги воспроизведения:_*
                         * Добавляем товар
                         * Переходим в корзину
                         * Нажимаем оформить заказ
                            
                        *_Ожидаемый результат:_* Кнопка 'Оплатить' должна быть в нижнем правом углу
                        """,
                "ОС: MacOS Sonoma 14.6.1, Chrome 131.0.6778.70(arm64)");
    }
}
