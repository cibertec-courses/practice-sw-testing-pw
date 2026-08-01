package edu.pe.cibertec.utils;

public class Environment {

    private Environment (){

    }
    public static String baseUrl(){
        return System.getProperty("baseUrl", "https://practicesoftwaretesting.com");
    }

    public static String browser(){
        return System.getProperty("browser", "chromium").toLowerCase();
    }

    public static boolean headless(){
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public static double sloMo(){
        return Double.parseDouble(System.getProperty("sloMo", "0"));
    }
    public static double timeout(){
        return Double.parseDouble(System.getProperty("timeMo", "30000"));
    }


}
