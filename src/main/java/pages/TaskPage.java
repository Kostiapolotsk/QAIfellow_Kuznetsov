package pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TaskPage {
    private final String inTaskName = "//input[@id='quickSearchInput']";
    private final String iconTask = "//a[@class='icon-container']";
    public static final String statusVal = "//span[@id='status-val']";
    public static final String statusVersion = "//span[@class='shorten']";

    public TaskPage openTask(String taskName) {
        $x(inTaskName).setValue(taskName).click();
        $x(iconTask).click();
        return this;
    }

    public TaskPage verifyTaskDetails(String status, String version) {
        $x(statusVal).shouldHave(text(status));
        $x(statusVersion).shouldHave(text(version));
        return this;
    }
}
