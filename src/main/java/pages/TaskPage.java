package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TaskPage {
    SelenideElement inTaskName = $x("//input[@id='quickSearchInput']").as("Поле ввода - Поиск");
    SelenideElement iconTask = $x("//a[@class='icon-container']").as("В окне ЗАПРОСЫ графа - TestSeleniumATHomework");
    SelenideElement statusVal = $x("//span[@id='status-val']").as("Статус графы - Статус");
    SelenideElement statusVersion = $x("//span[@class='shorten']").as("Статус графы - Исправить в версиях: ");

    public TaskPage openTask(String taskName) {
        inTaskName.setValue(taskName).click();
        iconTask.click();
        return this;
    }

    public TaskPage verifyTaskDetails(String status, String version) {
        statusVal.shouldHave(text(status));
        statusVersion.shouldHave(text(version));
        return this;
    }
}
