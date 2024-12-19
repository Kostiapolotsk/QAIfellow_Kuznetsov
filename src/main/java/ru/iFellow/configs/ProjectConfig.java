package ru.iFellow.configs;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ProjectConfig extends Config {

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("baseUrl")
    String baseUrl();
}
