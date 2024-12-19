package fifthLesson;

import fifthLesson.config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestReqres {
    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = config.baseUrl();
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    public void checkPostUser() {
        File requestBody = new File("src/test/resources/fifthLesson/jsons/user.json");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .post("api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("name", equalTo("Tomato"))
                .body("job", equalTo("Eat maket"))
                .extract()
                .response();

        System.out.println("Response: " + response.getBody().prettyPrint());
    }
}