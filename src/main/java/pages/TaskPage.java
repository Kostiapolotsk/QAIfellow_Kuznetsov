package pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TaskPage {
    public TaskPage openTask(String taskName) {
        $x("//input[@id='quickSearchInput']").setValue(taskName).click();
        $x("//a[@class='icon-container']").click();
        return this;
    }

    public TaskPage verifyTaskDetails(String status, String version) {
        $x("//span[@id='status-val']").shouldHave(text(status));
        $x("//span[@class='shorten']").shouldHave(text(version));
        return this;
    }

    public TaskPage changeTaskStatus(String newStatus) {
        $x("//button[text()='Изменить статус']").click();
        $x("//button[text()='" + newStatus + "']").click();
        $x("//span[text()='Статус']").shouldHave(text(newStatus)); // Проверяем, что статус изменился
        return this;
    }
}