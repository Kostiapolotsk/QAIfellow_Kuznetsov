package steps;

import io.cucumber.java.en.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import config.ApiConfig;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static steps.Steps.logResponse;

public class ReqresSteps {

    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);
    private Response response;

    @Given("базовый URL")
    public void setupBaseUrl() {
        config.baseUrl();
    }

    @When("отправлен POST запрос для создания пользователя с телом из файла {string}")
    public void sendPostRequest(String body) {
        File requestBody = new File("src/test/resources/jsons/" + body);

        response = given()
                .filter(new AllureRestAssured())
                .header("Content-Type", config.contentType())
                .body(requestBody)
                .log().all()
                .post(config.baseUrl() + "/api/users");
        logResponse(response);
    }

    @Then("статус код должен быть {int}")
    public void verifyStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("имя пользователя равно {string}")
    public void verifyUserName(String expectedName) {
        response.then().body("name", equalTo(expectedName));
    }

    @And("работа пользователя равна {string}")
    public void verifyUserJob(String expectedJob) {
        response.then().body("job", equalTo(expectedJob));
    }
}
