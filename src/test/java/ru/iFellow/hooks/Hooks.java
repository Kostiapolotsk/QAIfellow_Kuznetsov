package ru.iFellow.hooks;

import com.codeborne.selenide.Selenide;
import ru.iFellow.configs.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.iFellow.pages.LoginPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Hooks {
    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @BeforeEach
    public void initBrowser() {
        Selenide.open(config.baseUrl());
        getWebDriver().manage().window().maximize();
        login();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    public void login() {
        String user = config.login();
        String password = config.password();
        LoginPage loginPage = new LoginPage();
        loginPage.login(user, password);
    }
}