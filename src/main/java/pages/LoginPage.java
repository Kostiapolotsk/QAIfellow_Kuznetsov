package pages;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    public LoginPage openPage() {
        open("https://edujira.ifellow.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        return this;
    }

    public LoginPage login(String username, String password) {
        $x("//input[@name='os_username']").setValue(username);
        $x("//input[@name='os_password']").setValue(password);
        $x("//input[@class='aui-button aui-button-primary']").click();
        return this;
    }
}
