package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbsCommon {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;

    protected static final Logger logger = LogManager.getLogger(AbsCommon.class);

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);
        logger.info("WebDriver инициализирован в AbsCommon.");
    }
}
