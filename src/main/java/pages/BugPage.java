package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BugPage {
    SelenideElement buttonSelectTaskOrBug = $x("//button[@aria-controls='iic-issue-type-dropdown-c1098']").as("Кнопка выбора задачи или бага");
    SelenideElement buttonSelectBugFromTheList = $x("//a[@data-id='10102']").as("Кнопка выбора бага из списка");
    SelenideElement fieldEnteringNameBug = $x("//button[@class='aui-button aui-button-text iic-widget__more']").as("Плейсхолждер - Открыть в диалоговом окне");
    SelenideElement fieldEnteringSubject = $x("//label[@for='summary']").as("Поле ввода темы бага");
    SelenideElement fieldEnteringVisible = $x("//input[@class='text long-field focus-visible']").as("Окно ввода - Описания");
    SelenideElement windowEnteringDescription = $x("//label[@for='description']").as("Рамка ввода - Описания");
    SelenideElement fieldEnteringDescription = $x("//textarea[@id='description']").as("Поле ввода - Описания");
    SelenideElement windowEnteringEnvironment = $x("//label[@for='environment']").as("Слово - Окружение");
    SelenideElement fieldEnteringEnvironment = $x("//textarea[@id='environment']").as("Поле ввода - Окружение");
    SelenideElement buttonCreateWindowTask = $x("//input[@id='create-issue-submit']").as("Кнопка создать на рамке - создание задачи");


    public BugPage createNewBug(String subject, String description, String environment) {
        buttonSelectTaskOrBug.click();
        buttonSelectBugFromTheList.click();
        fieldEnteringNameBug.click();
        fieldEnteringSubject.click();
        fieldEnteringVisible.setValue(subject);
        windowEnteringDescription.click();
        fieldEnteringDescription.setValue(description);
        windowEnteringEnvironment.click();
        fieldEnteringEnvironment.setValue(environment);
        buttonCreateWindowTask.click();
        return this;
    }
}
