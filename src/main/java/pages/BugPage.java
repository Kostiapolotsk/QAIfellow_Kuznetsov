package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BugPage {
    SelenideElement buttonSelectTaskOrBug = $x("//button[@aria-controls='iic-issue-type-dropdown-c1098']");
    SelenideElement buttonSelectBugFromTheList = $x("//a[@data-id='10102']");
    SelenideElement fieldEnteringNameBug = $x("//button[@class='aui-button aui-button-text iic-widget__more']");
    SelenideElement fieldEnteringSubject = $x("//label[@for='summary']");
    SelenideElement fieldEnteringVisible = $x("//input[@class='text long-field focus-visible']");
    SelenideElement windowEnteringDescription = $x("//label[@for='description']");
    SelenideElement fieldEnteringDescription = $x("//textarea[@id='description']");
    SelenideElement windowEnteringEnvironment = $x("//label[@for='environment']");
    SelenideElement fieldEnteringEnvironment = $x("//textarea[@id='environment']");
    SelenideElement buttonCreateWindowTask = $x("//input[@id='create-issue-submit']");


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
