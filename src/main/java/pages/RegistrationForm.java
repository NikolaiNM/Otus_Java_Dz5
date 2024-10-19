package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationForm extends AbsBasePage {

    protected String username;
    protected String password;
    protected String email;

    public RegistrationForm(WebDriver driver) {
        super(driver);
        this.username = System.getProperty("username", "Nikolay");
        this.password = System.getProperty("password", "12345678");
        this.email = System.getProperty("email", "Test@email.com");
    }

    // Метод для получения имени пользователя
    public String getUsername() {
        return username;
    }

    // Метод для получения пароля
    public String getPassword() {
        return password;
    }

    // Метод получения почты
    public String getEmail() {
        return  email;
    }

//    // Метод для поиска элемента по CSS-селектору
//    public WebElement findByCssSelector(String cssSelector) {
//        return driver.findElement(By.cssSelector(cssSelector)); // Возвращаем найденный элемент
//    }
//
//    // Метод для клика по элементу
//    public RegistrationForm clickElement(WebElement element) {
//        element.click();  // Кликаем по переданному элементу
//        return this;  // Возвращаем текущий экземпляр для цепочного вызова
//    }
//
//    // Метод для ввода текста в выбранный элемент
//    public RegistrationForm enterText(WebElement element, String text) {
//        element.sendKeys(text); // Вводим текст в найденный элемент
//        return this; // Возвращаем текущий экземпляр для цепочного вызова
//    }


//    // Метод для проверки заголовка
//    public RegistrationForm headerShouldBeSameAs(String cssSelector, String expectedHeader) {
//        WebElement header = findByCssSelector(cssSelector);  //    Находим заголовок по CSS-селектору
//        Assertions.assertEquals(
//                expectedHeader,
//                header.getText(),  // Сравниваем текст заголовка с ожидаемым
//                "Error: Заголовок не соответствует ожидаемому"
//        );
//        return this;  // Возвращаем текущий экземпляр для цепочного вызова
//    }

}
