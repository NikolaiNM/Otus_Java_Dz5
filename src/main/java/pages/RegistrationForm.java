package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegistrationForm extends AbsBasePage {

    protected String username;
    protected String password;
    protected String email;

    // Локаторы
    private static final String USERNAME_SELECTOR = "#username";
    private static final String EMAIL_SELECTOR = "#email";
    private static final String PASSWORD_SELECTOR = "#password";
    private static final String CONFIRM_PASSWORD_SELECTOR = "#confirm_password";
    private static final String BIRTHDATE_SELECTOR = "#birthdate";
    private static final String LANGUAGE_LEVEL_SELECTOR = "#language_level";
    private static final String REGISTER_BUTTON_SELECTOR = "input[type='submit']";
    private static final String OUTPUT_SELECTOR = "#output";


    public RegistrationForm(WebDriver driver) {
        super(driver);
        this.username = System.getProperty("username", "Nikolay");
        this.password = System.getProperty("password", "12345678");
        this.email = System.getProperty("email", "Test@email.com");
    }

    public void enterUsername() {
        WebElement usernameElement = findByCssSelector(USERNAME_SELECTOR);
        enterText(usernameElement, username);
        logger.info("Имя пользователя введено: {}", username);
    }

    public void enterEmail() {
        WebElement emailElement = findByCssSelector(EMAIL_SELECTOR);
        enterText(emailElement, email);
        logger.info("Электронная почта введена: {}", email);
    }

    public void enterPassword() {
        WebElement passwordElement = findByCssSelector(PASSWORD_SELECTOR);
        enterText(passwordElement, password);
        logger.info("Пароль введён.");
    }

    public void enterConfirmPassword() {
        WebElement confirmPasswordElement = findByCssSelector(CONFIRM_PASSWORD_SELECTOR);
        enterText(confirmPasswordElement, password); // Вводим подтверждение пароля
        logger.info("Пароль подтверждён.");
    }

    public void checkPasswordsMatch() {
        WebElement passwordElement = findByCssSelector(PASSWORD_SELECTOR);
        WebElement confirmPasswordElement = findByCssSelector(CONFIRM_PASSWORD_SELECTOR);

        String passwordValue = passwordElement.getAttribute("value"); // Получаем значение пароля
        String confirmPasswordValue = confirmPasswordElement.getAttribute("value"); // Получаем значение подтверждения

        // Проверяем совпадение паролей
        if (passwordValue == null || !passwordValue.equals(confirmPasswordValue)) {
            // Возможно выводить значение пароля плохая идея?
            logger.error("Пароли не совпадают. Пароль: '{}' Подтвержденный пароль: '{}'", passwordValue, confirmPasswordValue);
            throw new AssertionError(String.format("Пароли не совпадают. Пароль '%s' Подтвержденный пароль '%s'", passwordValue, confirmPasswordValue)); // Генерируем ошибку, если пароли не совпадают
        }
        logger.info("Пароли совпадают.");
    }

    // Вводим дату рождения
    public void enterBirthdate(String birthdate) {
        WebElement birthdateElement = findByCssSelector(BIRTHDATE_SELECTOR);

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
        WebElement languageLevelElement = findByCssSelector(LANGUAGE_LEVEL_SELECTOR);
        selectDropdownByValue(languageLevelElement, value);
        logger.info("Выбран уровень языка: {}", value);
    }

    // Метод нажатия на кнопку Зарегистрироваться
    public void clickRegisterButton() {
        WebElement registerButton = findByCssSelector(REGISTER_BUTTON_SELECTOR);
        registerButton.click();
        logger.info("Кнопка Зарегистрироваться нажата.");
    }

    // Метод для проверки вывода данных
    public void verifyOutput(String birthdate, String languageLevel) {
        WebElement outputElement = findByCssSelector(OUTPUT_SELECTOR);
        String outputText = outputElement.getText();

        String expectedText = String.format("Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                username, email, formatDateToISO(birthdate), languageLevel);

        Assertions.assertEquals(expectedText, outputText, "Данные не совпадают с выводом!");
        logger.info("Проверка вывода данных прошла успешно.");
    }
}
