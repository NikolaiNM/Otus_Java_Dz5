package factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class FirefoxSettings implements IWebDriverSettings {

    @Override
    public AbstractDriverOptions setting() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return firefoxOptions;
    }

}
