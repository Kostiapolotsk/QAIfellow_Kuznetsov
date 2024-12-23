package steps;

import config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class Steps {
    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);
    private static final Logger logger = LoggerFactory.getLogger(Steps.class);

    public static Response getMortyResponse() {
        Response response = given()
                .filter(new AllureRestAssured())
                .baseUri(config.mortyUrl())
                .log().all()
                .queryParam("name", "Morty Smith")
                .get("/character")
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
        logResponse(response);
        return response;
    }

    public static Response getResponseFromUrl(String url) {
        Response response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .get(url)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
        logResponse(response);
        return response;
    }

    public static int getLastEpisodeNumber(Response responseMorty) {
        return responseMorty.jsonPath().getList("results[0].episode").size() - 1;
    }

    public static String getEpisodeUrl(Response responseMorty, int episodeNumber) {
        return responseMorty.jsonPath().getList("results[0].episode").get(episodeNumber).toString();
    }

    public static String getLastCharacterUrl(Response responseLastEpisode) {
        int lastCharacterIndex = responseLastEpisode.jsonPath().getList("characters").size() - 1;
        return responseLastEpisode.jsonPath().getList("characters").get(lastCharacterIndex).toString();
    }

    public static String getCharacterMorty(Response response) {
        return response.jsonPath().getString("results[0].species");
    }

    public static String getCharacterLast(Response response) {
        return response.jsonPath().getString("species");
    }

    public static String getMortyLocation(Response response) {
        return response.jsonPath().getString("results[0].location.name");
    }

    public static String getLastCharacterLocation(Response response) {
        return response.jsonPath().getString("location.name");
    }

    @SuppressWarnings("unchecked")
    static void logResponse(Response response) {
        logger.info("API Response Status Code: {}", response.getStatusCode());
        logger.info("API Response Headers: {}", response.getHeaders().asList());
        logger.info("API Response Body: {}", response.prettyPrint());
    }
}
