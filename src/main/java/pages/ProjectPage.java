package pages;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    public ProjectPage openProject(String projectName) {
        $x("//a[text()='" + projectName + "']").click();
        $x("//a[@id='admin_main_proj_link_lnk']").click();
        $x("//button[@id='subnav-trigger']").click();
        $x("//a[@data-item-id='allissues']").click();
        $x("//span[@class='aui-icon aui-icon-small aui-iconfont-add']").click();
        $x("//div[@class='iic-widget__issue-type-selector']").click();
        sleep(1000);
        return this;
    }

    public int getTotalTasks() {
        String taskInfo = $x("//div[@class='showing']").getText();
        String totalTasks = taskInfo.split("из")[1].trim();
        return Integer.parseInt(totalTasks);
    }

    public ProjectPage createNewTask(String description) {
        $x("//button[@aria-controls='iic-issue-type-dropdown-c1098']").click();
        $x("//a[@data-id='10100']").click();
        $x("//textarea[@name='summary']").setValue(description).pressEnter();
        sleep(1000);
        return this;
    }
}