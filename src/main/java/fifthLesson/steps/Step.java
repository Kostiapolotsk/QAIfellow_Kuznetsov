package fifthLesson.steps;

import fifthLesson.config.ApiConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class Step {
    public static Response getMortyResponse() {
        return given()
                .baseUri(ApiConfig.MORTY_URL)
                .log().all()
                .queryParam("name", "Morty Smith")
                .get("/character")
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    public static Response getResponseFromUrl(String url) {
        return given()
                .baseUri(ApiConfig.MORTY_URL)
                .log().all()
                .get(url)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
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
}
