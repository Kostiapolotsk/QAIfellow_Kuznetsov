package pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    SelenideElement buttonCreate = $x("//a[@class='aui-button aui-button-primary aui-style create-issue ']").as("Кнопка - Создать");
    SelenideElement userName = $x("//input[@name='os_username']").as("Поле ввода логина");
    SelenideElement userPassword = $x("//input[@name='os_password']").as("Поле ввода пароля");
    SelenideElement buttonEntry = $x("//input[@class='aui-button aui-button-primary']").as("Кнопка - Вход");

    public LoginPage login(String username, String password) {
        userName.setValue(username);
        userPassword.setValue(password);
        buttonEntry.click();
        return this;
    }

    public void buttonCreate(String expectedText) {
        Assert.assertEquals(buttonCreate.getText(), expectedText);
    }
}
