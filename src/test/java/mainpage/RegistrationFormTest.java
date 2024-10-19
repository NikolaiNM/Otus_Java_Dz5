package mainpage;

import factory.WebDriverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

//    @AfterEach
//    public void close() {
//        if(this.driver !=null) {
//            this.driver.quit();
//        }
//    }

    @Test
    public void checkHeader() {
        WebElement nameElement = onPage.findByCssSelector("#username");
        onPage.enterText(nameElement, onPage.getUsername());
        WebElement emailElement = onPage.findByCssSelector("#email");
        onPage.enterText(emailElement, onPage.getEmail());
        WebElement passwordElement = onPage.findByCssSelector("#password");
        onPage.enterText(passwordElement, onPage.getPassword());
        WebElement confirmPasswordElement = onPage.findByCssSelector("#confirm_password");
        onPage.enterText(confirmPasswordElement, onPage.getPassword());

    }

}
