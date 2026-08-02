package edu.pe.cibertec.utils;

import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Environment {

    public static final EnvironmentVariables ENV =
            SystemEnvironmentVariables.createEnvironmentVariables();

    private Environment() {

    }

    private static String prop(String key, String defaultValue) {
        return ENV.getProperty(key, defaultValue);
    }

    public static String baseUrl() {
        return System.getProperty("baseUrl", "https://practicesoftwaretesting.com");
    }

    public static String browser() {
        return System.getProperty("browser", "chromium").toLowerCase();
    }

    public static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public static double sloMo() {
        return Double.parseDouble(System.getProperty("sloMo", "0"));
    }

    public static double timeout() {
        return Double.parseDouble(System.getProperty("timeout", "30000"));
    }


}
