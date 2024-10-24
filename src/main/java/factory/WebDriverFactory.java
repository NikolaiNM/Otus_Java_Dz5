package factory;

import exeption.BrowserNotFoundException;
import factory.impl.ChromeSettings;
import factory.impl.FirefoxSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser", "chrome");


    public WebDriver getDriver() {
        switch (browserName.toLowerCase()) {

            case "chrome": {
                ChromeSettings settings = new ChromeSettings();
                AbstractDriverOptions options = settings.setting();
                if (options instanceof ChromeOptions) {
                    return new ChromeDriver((ChromeOptions) options);
                }
                break;
            }

            case "firefox": {
                FirefoxSettings settings = new FirefoxSettings();
                AbstractDriverOptions options = settings.setting();
                if (options instanceof FirefoxOptions) {
                    return new FirefoxDriver((FirefoxOptions) options);
                }
                break;
            }

            default:
                throw new BrowserNotFoundException(browserName);
        }
        throw new BrowserNotFoundException("Неизвестный режим для браузера " + browserName);
    }
}
