package pages;

import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    private final String buttonProject = "//a[@aria-controls='browse_link-content']";
    private final String buttonProjectTest = "//a[@id='admin_main_proj_link_lnk']";
    private final String stringSwitchFilter = "//button[@id='subnav-trigger']";
    private final String stringAllTasks = "//a[@data-item-id='allissues']";
    private final String buttonCreateTask = "//span[@class='aui-icon aui-icon-small aui-iconfont-add']";
    private final String issueTypeSelector = "//div[@class='iic-widget__issue-type-selector']";
    private final String taskInfoForGetTotalTasks = "//div[@class='showing']";
    private final String stringCreateTask = "//button[@aria-controls='iic-issue-type-dropdown-c1098']";
    private final String buttonCreateTaskFromTheList = "//a[@data-id='10100']";
    private final String enteringADescription = "//textarea[@name='summary']";
    public static final String forAssertAllTasks = "//span[@class='subnavigator-title']";

    public ProjectPage openProject() {
        $x(buttonProject).click();
        $x(buttonProjectTest).click();
        $x(stringSwitchFilter).click();
        $x(stringAllTasks).click();
        $x(buttonCreateTask).click();
        $x(issueTypeSelector).click();
        sleep(1000);
        return this;
    }

    public int getTotalTasks() {
        String taskInfo = $x(taskInfoForGetTotalTasks).getText();
        String totalTasks = taskInfo.split("из")[1].trim();
        return Integer.parseInt(totalTasks);
    }

    public ProjectPage createNewTask(String description) {
        $x(stringCreateTask).click();
        $x(buttonCreateTaskFromTheList).click();
        $x(enteringADescription).setValue(description).pressEnter();
        sleep(1000);
        return this;
    }
}
