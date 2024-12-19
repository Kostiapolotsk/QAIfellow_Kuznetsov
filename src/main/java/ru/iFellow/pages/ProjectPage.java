package ru.iFellow.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    SelenideElement buttonProject = $x("//a[@aria-controls='browse_link-content']").as("Кнопка Проекты");
    SelenideElement buttonProjectTest = $x("//a[@id='admin_main_proj_link_lnk']").as("Кнопка Test (TEST)");
    SelenideElement stringSwitchFilter = $x("//button[@id='subnav-trigger']").as("Меню - Переключить фильтр");
    SelenideElement stringAllTasks = $x("//a[@data-item-id='allissues']").as("Кнопка - Все задачи");
    SelenideElement buttonCreateTask = $x("//span[@class='aui-icon aui-icon-small aui-iconfont-add']").as("Кнопка - Создать задачу");
    SelenideElement issueTypeSelector = $x("//div[@class='iic-widget__issue-type-selector']").as("Поле для выбора - История, Задача, Ошибка, Epic ");
    SelenideElement taskInfoForGetTotalTasks = $x("//div[@class='showing']").as("Счетчик кол-ва задач");
    SelenideElement stringCreateTask = $x("//button[@aria-controls='iic-issue-type-dropdown-c1098']").as("Кнопки для выбора - История, Задача, Ошибка, Epic ");
    SelenideElement buttonCreateTaskFromTheList = $x("//a[@data-id='10100']").as("Кнопка - Задача в сплывающем меню");
    SelenideElement enteringADescription = $x("//textarea[@name='summary']").as("Поле для ввода - Что должно быть сделано");
    SelenideElement forAssertAllTasks = $x("//span[@class='subnavigator-title']").as("Строка в верхнем левом угру - Все задачи");

    @Step("Открываем проект")
    public void openProject() {
        buttonProject.click();
        buttonProjectTest.click();
        stringSwitchFilter.click();
        stringAllTasks.click();
        buttonCreateTask.click();
        issueTypeSelector.click();
        sleep(1000);
    }

    @Step("Получаем кол-во задач")
    public int getTotalTasks() {
        String taskInfo = taskInfoForGetTotalTasks.getText();
        String totalTasks = taskInfo.split("из")[1].trim();
        return Integer.parseInt(totalTasks);
    }

    @Step("Создаем новую задачу")
    public void createNewTask(String description) {
        stringCreateTask.click();
        buttonCreateTaskFromTheList.click();
        enteringADescription.setValue(description).pressEnter();
        sleep(1000);
    }

    @Step("Проверка отображения - Все задачи ")
    public void assertAllTasksText(String expectedText) {
        Assert.assertEquals(forAssertAllTasks.getText(), expectedText);
    }
}
