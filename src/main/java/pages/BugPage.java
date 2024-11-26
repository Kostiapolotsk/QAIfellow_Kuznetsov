package pages;

import static com.codeborne.selenide.Selenide.$x;

public class BugPage {

    public BugPage createNewBug(String subject, String description, String environment) {
        $x("//button[@aria-controls='iic-issue-type-dropdown-c1098']").click();
        $x("//a[@data-id='10102']").click();
        $x("//button[@class='aui-button aui-button-text iic-widget__more']").click();
        $x("//label[@for='summary']").click();
        $x("//input[@class='text long-field focus-visible']").setValue(subject);
        $x("//label[@for='description']").click();
        $x("//textarea[@id='description']").setValue(description);
        $x("//label[@for='environment']").click();
        $x("//textarea[@id='environment']").setValue(environment);
        $x("//input[@id='create-issue-submit']").click();
        return this;
    }
}
