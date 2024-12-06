package pages;

import static com.codeborne.selenide.Selenide.$x;

public class BugPage {
    private String buttonSelectTaskOrBug = "//button[@aria-controls='iic-issue-type-dropdown-c1098']";
    private String buttonSelectBugFromTheList = "//a[@data-id='10102']";
    private String fieldEnteringNameBug = "//button[@class='aui-button aui-button-text iic-widget__more']";
    private String fieldEnteringSubject = "//label[@for='summary']";
    private String fieldEnteringVisible = "//input[@class='text long-field focus-visible']";
    private String windowEnteringDescription = "//label[@for='description']";
    private String fieldEnteringDescription = "//textarea[@id='description']";
    private String windowEnteringEnvironment = "//label[@for='environment']";
    private String fieldEnteringEnvironment = "//textarea[@id='environment']";
    private String buttonCreateWindowTask = "//input[@id='create-issue-submit']";

    public BugPage createNewBug(String subject, String description, String environment) {
        $x(buttonSelectTaskOrBug).click();
        $x(buttonSelectBugFromTheList).click();
        $x(fieldEnteringNameBug).click();
        $x(fieldEnteringSubject).click();
        $x(fieldEnteringVisible).setValue(subject);
        $x(windowEnteringDescription).click();
        $x(fieldEnteringDescription).setValue(description);
        $x(windowEnteringEnvironment).click();
        $x(fieldEnteringEnvironment).setValue(environment);
        $x(buttonCreateWindowTask).click();
        return this;
    }
}
