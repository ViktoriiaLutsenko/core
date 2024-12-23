package com.vlutsenko.framework.page;

import com.vlutsenko.framework.service.property.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractComponent {
    protected WebDriverWait wait;
    protected WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(PropertiesLoader.getProperty("timeout"))));
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isAt();


}
