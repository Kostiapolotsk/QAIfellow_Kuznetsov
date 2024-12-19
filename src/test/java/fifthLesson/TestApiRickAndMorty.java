package fifthLesson;

import fifthLesson.steps.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestApiRickAndMorty {
    @Test
    @DisplayName("Проверка Морти и последнего персонажа на рассу и местоположение")
    public void checkGetRickAndMorty1() {
        Response responseMorty = Step.getMortyResponse();
        int numberLastEpisode = Step.getLastEpisodeNumber(responseMorty);
        String mortyLastEpisode = Step.getEpisodeUrl(responseMorty, numberLastEpisode);

        Response responseLastEpisode = Step.getResponseFromUrl(mortyLastEpisode);
        String lastCharacterUrl = Step.getLastCharacterUrl(responseLastEpisode);

        Response lastCharacterResponse = Step.getResponseFromUrl(lastCharacterUrl);

        String mortyRace = Step.getCharacterMorty(responseMorty);
        String mortyLocation = Step.getMortyLocation(responseMorty);
        String lastCharacterRace = Step.getCharacterLast(lastCharacterResponse);
        String lastCharacterLocation = Step.getLastCharacterLocation(lastCharacterResponse);

        Assertions.assertFalse(mortyRace.equals(lastCharacterRace) && mortyLocation.equals(lastCharacterLocation));
    }
}
