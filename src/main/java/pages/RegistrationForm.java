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

    public void enterUsername() {
        WebElement usernameElement = findByCssSelector("#username");
        enterText(usernameElement, getUsername());
        logger.info("Имя пользователя введено: {}", getUsername());
    }

    public void enterEmail() {
        WebElement emailElement = findByCssSelector("#email");
        enterText(emailElement, getEmail());
        logger.info("Электронная почта введена: {}", getEmail());
    }

    public void enterPassword() {
        WebElement passwordElement = findByCssSelector("#password");
        enterText(passwordElement, getPassword());
        logger.info("Пароль введён.");
    }

    public void enterConfirmPassword() {
        WebElement confirmPasswordElement = findByCssSelector("#confirm_password");
        enterText(confirmPasswordElement, getPassword()); // Вводим подтверждение пароля
        logger.info("Пароль подтверждён.");
    }

    public void checkPasswordsMatch() {
        WebElement passwordElement = findByCssSelector("#password");
        WebElement confirmPasswordElement = findByCssSelector("#confirm_password");

        String passwordValue = passwordElement.getAttribute("value"); // Получаем значение пароля
        String confirmPasswordValue = confirmPasswordElement.getAttribute("value"); // Получаем значение подтверждения

        // Проверяем совпадение паролей
        if (passwordValue == null || !passwordValue.equals(confirmPasswordValue)) {
            logger.error("Пароли не совпадают. Пароль: '{}' Подтвержденный пароль: '{}'", passwordValue, confirmPasswordValue);
            throw new AssertionError(String.format("Пароли не совпадают. Пароль '%s' Подтвержденный пароль '%s'", passwordValue, confirmPasswordValue)); // Генерируем ошибку, если пароли не совпадают
        }
        logger.info("Пароли совпадают.");
    }

    // Вводим дату рождения
    public void enterBirthdate(String birthdate) {
        WebElement birthdateElement = findByCssSelector("#birthdate");

        // Преобразуем дату в формат 'ГГГГ-ММ-ДД', если она задана в формате 'ДД.ММ.ГГГГ'
        String formattedDate = formatDateToISO(birthdate);

        // Устанавливаем дату через JavaScript, чтобы она корректно работала во всех браузерах
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", birthdateElement, formattedDate);
        logger.info("Дата рождения введена: {}", formattedDate);
    }

    // Преобразуем дату в date формат 'ГГГГ-ММ-ДД'
    private String formatDateToISO(String date) {
        String[] parts = date.split("\\."); // Разделяем дату по точкам

        if (parts.length != 3) {
            logger.error("Неверный формат даты. Ожидался формат 'ДД.ММ.ГГГГ'. Получено: {}", date);
            throw new IllegalArgumentException("Неверный формат даты. Ожидался формат 'ДД.ММ.ГГГГ'.");
        }

        // Преобразуем в формат 'ГГГГ-ММ-ДД'
        return String.format("%s-%s-%s", parts[2], parts[1], parts[0]);
    }

    // Метод выбора языка
    public void selectLanguage(String value) {
        WebElement languageLevelElement = findByCssSelector("#language_level");
        selectDropdownByValue(languageLevelElement, value);
        logger.info("Выбран уровень языка: {}", value);
    }

    // Метод нажатия на кнопку Зарегистрироваться
    public void clickRegisterButton() {
        WebElement registerButton = findByCssSelector("input[type='submit']");
        registerButton.click();
        logger.info("Кнопка Зарегистрироваться нажата.");
    }

    // Метод для проверки вывода данных
    public void verifyOutput(String birthdate, String languageLevel) {
        WebElement outputElement = findByCssSelector("#output");
        String outputText = outputElement.getText();

        String expectedText = String.format("Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                getUsername(), getEmail(), formatDateToISO(birthdate), languageLevel);

        Assertions.assertEquals(expectedText, outputText, "Данные не совпадают с выводом!");
        logger.info("Проверка вывода данных прошла успешно.");
    }
}
