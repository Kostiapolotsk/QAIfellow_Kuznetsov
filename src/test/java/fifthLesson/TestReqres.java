package fifthLesson;

import fifthLesson.config.ApiConfig;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestReqres {
    @BeforeAll
    public static void setup() {
        ApiConfig.setupBaseURIReqres();
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    public void checkPostUser() {
        File requestBody = new File("src/test/resources/fifthLesson/user.json");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .post("/users")
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
