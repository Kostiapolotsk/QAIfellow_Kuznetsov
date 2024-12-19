package fifthLesson.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import fifthLesson.config.ApiConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReqresSteps {

    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);
    private Response response;

    @Given("базовый URL")
    public void setupBaseUrl() {
      config.baseUrl();
    }

    @When("отправлен POST запрос для создания пользователя с телом:")
    public void sendPostRequest(String body) {
        response = given()
                .header("Content-Type", config.contentType())
                .body(body)
                .log().all()
                .post(config.baseUrl() + "/api/users");
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
