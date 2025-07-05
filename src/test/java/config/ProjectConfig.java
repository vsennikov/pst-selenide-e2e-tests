package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ProjectConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("customer.email")
    String customerEmail();

    @Key("customer.password")
    String customerPassword();
}
