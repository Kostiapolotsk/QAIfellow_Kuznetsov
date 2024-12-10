package fifthLesson;

import fifthLesson.config.ApiConfig;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestApiRickAndMorty {
    @Test
    @DisplayName("Проверка Морти и последнего персанажа на рассу и местоположение")
    public void checkGetRickAndMorty() {
        Response responseMorty = given()
                .baseUri(ApiConfig.MORTY_URL)
                .log().all()
                .queryParam("name", "Morty Smith")
                .get("/character").then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        int numberLastEpisode = responseMorty.jsonPath().getList("results[0].episode").size() - 1;
        String mortyLastEpisode = responseMorty.jsonPath().getList("results[0].episode").get(numberLastEpisode).toString();

        Response responseLastEpisode = given()
                .baseUri(ApiConfig.MORTY_URL)
                .log().all()
                .get(mortyLastEpisode)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        int numberLastCharacter = responseLastEpisode.jsonPath().getList("characters").size() - 1;
        String lastCharacter = responseLastEpisode.jsonPath().getList("characters").get(numberLastCharacter).toString();

        Response lastCharacterRaceAndLocation = given()
                .baseUri(ApiConfig.MORTY_URL)
                .log().all()
                .get(lastCharacter)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        String raceLastCharacter = lastCharacterRaceAndLocation.jsonPath().getString("species");
        String locationLastCharacter = lastCharacterRaceAndLocation.jsonPath().getString("location.name");
        String mortyRace = responseMorty.jsonPath().getString("results[0].species");
        String mortyLocation = responseMorty.jsonPath().getString("results[0].location.name");

        System.out.println("Расса Морти: " + mortyRace);
        System.out.println("Местоположение Морти: " + mortyLocation);
        System.out.println("Расса последнего персонажа: " + raceLastCharacter);
        System.out.println("Местопопложение последенго персонажа: " + locationLastCharacter);

        if (mortyRace.equals(raceLastCharacter) && mortyLocation.equals(locationLastCharacter)) {
            System.out.println("Последний персонаж такой же, как Морти");
        } else {
            System.out.println("Последний персонадж отличается от Морти");
        }
    }
}
