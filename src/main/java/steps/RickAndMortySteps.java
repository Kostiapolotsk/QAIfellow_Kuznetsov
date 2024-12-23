package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


import org.junit.Assert;

public class RickAndMortySteps {
    private Response responseMorty;
    private Response responseLastEpisode;
    private Response responseLastCharacter;

    @Given("отправлен запрос для поиска персонажа {string}")
    public void sentRequestToSearchForCharacter(String characterName) {
        responseMorty = Steps.getMortyResponse();
    }

    @When("найдена информация о последнем эпизоде Морти")
    public void foundInformationAboutTheLastEpisodeOfMorty() {
        int numberLastEpisode = Steps.getLastEpisodeNumber(responseMorty);
        String mortyLastEpisode = Steps.getEpisodeUrl(responseMorty, numberLastEpisode);
        responseLastEpisode = Steps.getResponseFromUrl(mortyLastEpisode);
    }

    @When("получена информация о последнем персонаже из последнего эпизода")
    public void getInformationAboutTheLastCharacterFromTheLastEpisodeReceived() {
        String lastCharacterUrl = Steps.getLastCharacterUrl(responseLastEpisode);
        responseLastCharacter = Steps.getResponseFromUrl(lastCharacterUrl);
    }

    @Then("раса Морти не совпадает с расой последнего персонажа ИЛИ местоположение Морти не совпадает с местоположением последнего персонажа")
    public void CheckingMortyAndTheLastCharacterForLocationAndRace() {
        String mortyRace = Steps.getCharacterMorty(responseMorty);
        String mortyLocation = Steps.getMortyLocation(responseMorty);
        String lastCharacterRace = Steps.getCharacterLast(responseLastCharacter);
        String lastCharacterLocation = Steps.getLastCharacterLocation(responseLastCharacter);
        Assert.assertFalse(mortyRace.equals(lastCharacterRace) && mortyLocation.equals(lastCharacterLocation));
    }
}
