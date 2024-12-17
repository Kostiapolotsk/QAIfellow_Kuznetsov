package ru.iFellow;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;

public class Hooks {
    @Getter
    private static final String user = "AT10";
    @Getter
    private static final String password = "Qwerty123";

    @BeforeEach
    public void initBrowser() {
        Selenide.open("https://edujira.ifellow.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        login();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    public void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(getUser(), getPassword());
    }
}
