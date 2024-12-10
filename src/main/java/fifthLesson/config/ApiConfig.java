package fifthLesson.config;

import io.restassured.RestAssured;

public class ApiConfig {
    public static final String MORTY_URL = "https://rickandmortyapi.com/api";

    public static void setupBaseURIReqres() {
        RestAssured.baseURI = "https://reqres.in/api";
    }
}
