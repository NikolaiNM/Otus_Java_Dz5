package registrationpage;

import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.RegistrationForm;

public class RegistrationFormTest {
    private final String birthdate = "30.09.1986";
    private final String languageLevel = "intermediate";
    private WebDriver driver;
    private RegistrationForm onPage; // Объявляем переменную для страницы

    @BeforeEach
    public void init() {
        this.driver = new WebDriverFactory().getDriver();
        this.onPage = new RegistrationForm(driver); // Инициализируем страницу
        onPage.open("/form.html"); // Открываем нужный URL

    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void checkingRegistration2() {
        onPage.enterUsername();
        onPage.enterEmail();
        onPage.enterPassword();
        onPage.enterConfirmPassword();
        onPage.checkPasswordsMatch();
        onPage.enterBirthdate(birthdate);
        onPage.selectLanguage(languageLevel);
        onPage.clickRegisterButton();
        onPage.verifyOutput(birthdate, languageLevel);
    }
}
