package com.vlutsenko.framework.config.driver;

public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String name;

    Browsers(String browser) {
        name = browser;
    }

    public String getName() {
        return name;
    }
}
