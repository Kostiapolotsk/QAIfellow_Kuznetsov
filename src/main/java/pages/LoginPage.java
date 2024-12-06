package pages;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private String url = "https://edujira.ifellow.ru";
    public final static String buttonCreate = "//a[@class='aui-button aui-button-primary aui-style create-issue ']";
    public String userName = "//input[@name='os_username']";
    public String userPassword = "//input[@name='os_password']";
    public String buttonEntry = "//input[@class='aui-button aui-button-primary']";

    public LoginPage openPage() {
        open(url);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        return this;
    }

    public LoginPage login(String username, String password) {
        $x(userName).setValue(username);
        $x(userPassword).setValue(password);
        $x(buttonEntry).click();
        return this;
    }
}
