package pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    SelenideElement buttonProject = $x("//a[@aria-controls='browse_link-content']");
    SelenideElement buttonProjectTest = $x("//a[@id='admin_main_proj_link_lnk']");
    SelenideElement stringSwitchFilter = $x("//button[@id='subnav-trigger']");
    SelenideElement stringAllTasks = $x("//a[@data-item-id='allissues']");
    SelenideElement buttonCreateTask = $x("//span[@class='aui-icon aui-icon-small aui-iconfont-add']");
    SelenideElement issueTypeSelector = $x("//div[@class='iic-widget__issue-type-selector']");
    SelenideElement taskInfoForGetTotalTasks = $x("//div[@class='showing']");
    SelenideElement stringCreateTask = $x("//button[@aria-controls='iic-issue-type-dropdown-c1098']");
    SelenideElement buttonCreateTaskFromTheList = $x("//a[@data-id='10100']");
    SelenideElement enteringADescription = $x("//textarea[@name='summary']");
    SelenideElement forAssertAllTasks = $x("//span[@class='subnavigator-title']");

    public ProjectPage openProject() {
        buttonProject.click();
        buttonProjectTest.click();
        stringSwitchFilter.click();
        stringAllTasks.click();
        buttonCreateTask.click();
        issueTypeSelector.click();
        sleep(1000);
        return this;
    }

    public int getTotalTasks() {
        String taskInfo = taskInfoForGetTotalTasks.getText();
        String totalTasks = taskInfo.split("из")[1].trim();
        return Integer.parseInt(totalTasks);
    }

    public ProjectPage createNewTask(String description) {
        stringCreateTask.click();
        buttonCreateTaskFromTheList.click();
        enteringADescription.setValue(description).pressEnter();
        sleep(1000);
        return this;
    }

    public void assertAllTasksText(String expectedText) {
        Assert.assertEquals(forAssertAllTasks.getText(), expectedText);
    }
}
