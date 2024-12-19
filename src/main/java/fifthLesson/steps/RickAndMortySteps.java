package fifthLesson.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class RickAndMortySteps {
    private Response responseMorty;
    private Response responseLastEpisode;
    private Response responseLastCharacter;
    private String lastCharacterUrl;

    @Given("отправлен запрос для поиска персонажа {string}")
    public void sentRequestToSearchForCharacter(String characterName) {
        responseMorty = Step.getMortyResponse();
    }

    @When("найдена информация о последнем эпизоде Морти")
    public void foundInformationAboutTheLastEpisodeOfMorty() {
        int numberLastEpisode = Step.getLastEpisodeNumber(responseMorty);
        String mortyLastEpisode = Step.getEpisodeUrl(responseMorty, numberLastEpisode);
        responseLastEpisode = Step.getResponseFromUrl(mortyLastEpisode);
    }

    @When("получена информация о последнем персонаже из последнего эпизода")
    public void getInformationAboutTheLastCharacterFromTheLastEpisodeReceived() {
        lastCharacterUrl = Step.getLastCharacterUrl(responseLastEpisode);
        responseLastCharacter = Step.getResponseFromUrl(lastCharacterUrl);
    }

    @Then("раса Морти не совпадает с расой последнего персонажа ИЛИ местоположение Морти не совпадает с местоположением последнего персонажа")
    public void CheckingMortyAndTheLastCharacterForLocationAndRace() {
        String mortyRace = Step.getCharacterMorty(responseMorty);
        String mortyLocation = Step.getMortyLocation(responseMorty);
        String lastCharacterRace = Step.getCharacterLast(responseLastCharacter);
        String lastCharacterLocation = Step.getLastCharacterLocation(responseLastCharacter);
        Assert.assertFalse(mortyRace.equals(lastCharacterRace) && mortyLocation.equals(lastCharacterLocation));
    }
}