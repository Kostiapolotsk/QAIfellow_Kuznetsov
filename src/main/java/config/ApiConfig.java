package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/application.properties")
public interface ApiConfig extends Config {
    @Key("morty.url")
    String mortyUrl();

    @Key("base.url")
    String baseUrl();

    @Key("content.type")
    String contentType();
}
