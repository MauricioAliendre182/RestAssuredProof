package org.example;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;

public class Utils {
    public static String setEnvVariableCookie(String variable) throws IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty("Cookie", variable);
        config.save();
        return variable;
    }

    public static String setEnvVariableToken(String variable) throws IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty("Token", variable);
        config.save();
        return variable;
    }
}
