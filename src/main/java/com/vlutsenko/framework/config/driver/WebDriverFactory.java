package com.vlutsenko.framework.config.driver;

import com.vlutsenko.framework.service.property.PropertiesLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class WebDriverFactory{

    private final Supplier<WebDriver> LOCAL_CHROME = () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };
    private final Supplier<WebDriver> LOCAL_FIREFOX = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    private final Supplier<RemoteWebDriver> REMOTE_CHROME = () -> {
        try {
            return new RemoteWebDriver(URI.create(PropertiesLoader.getProperty("selenium.grid.url")).toURL(), new ChromeOptions());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    };
    private final Supplier<RemoteWebDriver> REMOTE_FIREFOX = () -> {
        try {
            return new RemoteWebDriver(URI.create(PropertiesLoader.getProperty("selenium.grid.url")).toURL(), new FirefoxOptions());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    };

    private final Map<String, Supplier<WebDriver>> LOCAL_MAP = new HashMap<>();
    private final Map<String, Supplier<RemoteWebDriver>> REMOTE_MAP = new HashMap<>();

    {
        LOCAL_MAP.put(Browsers.CHROME.getName(), LOCAL_CHROME);
        LOCAL_MAP.put(Browsers.FIREFOX.getName(), LOCAL_FIREFOX);
        REMOTE_MAP.put(Browsers.CHROME.getName(), REMOTE_CHROME);
        REMOTE_MAP.put(Browsers.FIREFOX.getName(), REMOTE_FIREFOX);
    }

    public WebDriver getDriver() {
        boolean runMode = Boolean.parseBoolean(PropertiesLoader.getProperty("selenium.grid.enabled"));
        String browser = PropertiesLoader.getProperty("browser");

        if (runMode) {
            return REMOTE_MAP.get(browser).get();
        } else {
            return LOCAL_MAP.get(browser).get();
        }
    }
}
