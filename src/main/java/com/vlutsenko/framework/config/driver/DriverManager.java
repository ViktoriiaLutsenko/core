package com.vlutsenko.framework.config.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getWebDriver(){
        return driver.get();
    }

    public static void setWebDriver(WebDriver driverInstance){
        driver.set(driverInstance);
    }


}
