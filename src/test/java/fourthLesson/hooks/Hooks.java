package fourthLesson.hooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.*;

public class Hooks {
    @After
    public void afterSimpleTest() {
        Selenide.closeWebDriver();
    }
}
