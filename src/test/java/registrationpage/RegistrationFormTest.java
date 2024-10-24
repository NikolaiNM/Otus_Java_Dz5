package registrationpage;

import enums.registrationform.LanguageLevel;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.RegistrationForm;

public class RegistrationFormTest {

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
        String birthdate = "30.09.1986";
        LanguageLevel languageLevel = LanguageLevel.INTERMEDIATE;
        onPage.enterUsername();
        onPage.enterEmail();
        onPage.enterPassword();
        onPage.enterConfirmPassword();
        onPage.checkPasswordsMatch();
        onPage.enterBirthdate(birthdate);
        onPage.selectLanguage(languageLevel.getLanguageLevelValue());
        onPage.clickRegisterButton();
        onPage.verifyOutput(birthdate, languageLevel.getLanguageLevelValue());

    }
}
