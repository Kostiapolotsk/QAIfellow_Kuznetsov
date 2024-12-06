package fourthLessonSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.BugPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.TaskPage;

import static com.codeborne.selenide.Selenide.$x;

public class Steps {
    private String visibleButtonCreate = "//a[@class='aui-button aui-button-primary aui-style create-issue ']";
    private String headerIsDisplayedAllTasks = "//span[@class='subnavigator-title']";
    private String taskDetailsContainDo = "//span[@id='status-val']";
    private String taskDetailsContainVersion = "//span[@id='fixVersions-field']";
    LoginPage loginPage = new LoginPage();
    ProjectPage projectPage = new ProjectPage();
    BugPage bugPage = new BugPage();
    int initialTasks;

    @Given("пользователь открывает страницу логина")
    public void openLoginPage() {
        loginPage.openPage();
    }

    @When("пользователь вводит логин {string} и пароль {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("на странице отображается кнопка {string}")
    public void verifyButton(String buttonText) {
        Assertions.assertEquals($x(visibleButtonCreate)
                .getText(), buttonText);
    }

    @Given("пользователь авторизован с логином {string} и паролем {string}")
    public void userIsLoggedIn(String username, String password) {
        loginPage.openPage().login(username, password);
    }

    @When("пользователь переходит в проект {string}")
    public void openProject() {
        projectPage.openProject();
    }

    @Then("на странице отображается заголовок {string}")
    public void verifyTitle(String title) {
        Assertions.assertEquals($x(headerIsDisplayedAllTasks)
                .getText(), title);
    }

    @When("пользователь создает новую задачу с описанием {string}")
    public void createTask(String description) {
        initialTasks = projectPage.getTotalTasks();
        projectPage.createNewTask(description);
    }

    @Then("количество задач увеличивается на {int}")
    public void verifyTaskCount(int increment) {
        int updatedTasks = projectPage.getTotalTasks();
        Assertions.assertEquals(initialTasks + increment, updatedTasks);
    }

    @Then("детали задачи содержат {string} и {string}")
    public void verifyTaskDetails(String status, String version) {
        TaskPage taskPage = new TaskPage();
        taskPage.openTask("TestSeleniumATHomework");
        Assertions.assertEquals($x(taskDetailsContainDo).getText(), status);
        Assertions.assertEquals($x(taskDetailsContainVersion).getText(), version);
    }

    @When("пользователь создает новый баг с описанием {string}")
    public void createBug(String description) {
        bugPage.createNewBug(description, "Шаги воспроизведения", "Ожидаемый результат");
    }
}
