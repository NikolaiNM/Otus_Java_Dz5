package pages;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
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
        return email;
    }

    public RegistrationForm enterUsername() {
        WebElement usernameElement = findByCssSelector("#username");
        enterText(usernameElement, getUsername());
        return this;
    }

    public RegistrationForm enterEmail() {
        WebElement emailElement = findByCssSelector("#email");
        enterText(emailElement, getEmail());
        return this;
    }

    public RegistrationForm enterPassword() {
        WebElement passwordElement = findByCssSelector("#password");
        enterText(passwordElement, getPassword());
        return this;
    }

    public RegistrationForm enterConfirmPassword() {
        WebElement confirmPasswordElement = findByCssSelector("#confirm_password");
        enterText(confirmPasswordElement, getPassword()); // Вводим подтверждение пароля
        return this;
    }

    public RegistrationForm checkPasswordsMatch() {
        WebElement passwordElement = findByCssSelector("#password");
        WebElement confirmPasswordElement = findByCssSelector("#confirm_password");

        String passwordValue = passwordElement.getAttribute("value"); // Получаем значение пароля
        String confirmPasswordValue = confirmPasswordElement.getAttribute("value"); // Получаем значение подтверждения

        // Проверяем совпадение паролей
        if (!passwordValue.equals(confirmPasswordValue)) {
            throw new AssertionError(String.format("Пароли не совпадают. Пароль '%s' Подтвержденный пароль '%s'", passwordValue, confirmPasswordValue)); // Генерируем ошибку, если пароли не совпадают
        }
        return this;
    }

    // Вводим дату рождения
    public RegistrationForm enterBirthdate(String birthdate) {
        WebElement birthdateElement = findByCssSelector("#birthdate");

        // Преобразуем дату в формат 'ГГГГ-ММ-ДД', если она задана в формате 'ДД.ММ.ГГГГ'
        String formattedDate = formatDateToISO(birthdate);

        // Устанавливаем дату через JavaScript, чтобы она корректно работала во всех браузерах
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", birthdateElement, formattedDate);

        return this;
    }

    // Преобразуем дату в date формат 'ГГГГ-ММ-ДД'
    private String formatDateToISO(String date) {
        String[] parts = date.split("\\."); // Разделяем дату по точкам

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат даты. Ожидался формат 'ДД.ММ.ГГГГ'.");
        }

        // Преобразуем в формат 'ГГГГ-ММ-ДД'
        return String.format("%s-%s-%s", parts[2], parts[1], parts[0]);
    }

    // Метод выбора языка
    public RegistrationForm selectLanguage(String value) {
        WebElement languageLevelElement = findByCssSelector("#language_level");
        selectDropdownByValue(languageLevelElement, value);
        return this;
    }

    // Метод нажатия на кнопку Зарегистрироваться
    public RegistrationForm clickRegisterButton() {
        WebElement registerButton = findByCssSelector("input[type='submit']");
        registerButton.click();
        return this;
    }

    // Метод для проверки вывода данных
    public RegistrationForm verifyOutput(String birthdate, String languageLevel) {
        WebElement outputElement = findByCssSelector("#output");
        String outputText = outputElement.getText();

        String expectedText = String.format("Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                getUsername(), getEmail(), formatDateToISO(birthdate), languageLevel);

        Assertions.assertEquals(expectedText, outputText, "Данные не совпадают с выводом!");
        return this;
    }
}
