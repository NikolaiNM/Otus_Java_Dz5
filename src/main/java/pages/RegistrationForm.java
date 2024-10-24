package pages;

import enums.registrationform.InputField;
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

    public void enterUsername() {
        enterFieldData(InputField.USERNAME, username);
    }

    public void enterEmail() {
        enterFieldData(InputField.EMAIL, email);
    }

    public void enterPassword() {
        enterFieldData(InputField.PASSWORD, password);
    }

    public void enterConfirmPassword() {
        enterFieldData(InputField.CONFIRM_PASSWORD, password);
    }

    public void checkPasswordsMatch() {
        WebElement passwordElement = findByCssSelector(InputField.PASSWORD.getSelector());
        WebElement confirmPasswordElement = findByCssSelector(InputField.CONFIRM_PASSWORD.getSelector());

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
        WebElement birthdateElement = findByCssSelector(InputField.BIRTHDATE.getSelector());

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
        WebElement languageLevelElement = findByCssSelector(InputField.LANGUAGE_LEVEL.getSelector());
        selectDropdownByValue(languageLevelElement, value);
        logger.info("Выбран уровень языка: {}", value);
    }

    // Метод нажатия на кнопку Зарегистрироваться
    public void clickRegisterButton() {
        WebElement registerButton = findByCssSelector(InputField.REGISTER_BUTTON.getSelector());
        registerButton.click();
        logger.info("Кнопка Зарегистрироваться нажата.");
    }

    // Метод для проверки вывода данных
    public void verifyOutput(String birthdate, String languageLevel) {
        WebElement outputElement = findByCssSelector(InputField.OUTPUT.getSelector());
        String outputText = outputElement.getText();

        String expectedText = String.format("Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                username, email, formatDateToISO(birthdate), languageLevel);

        Assertions.assertEquals(expectedText, outputText, "Данные не совпадают с выводом!");
        logger.info("Проверка вывода данных прошла успешно.");
    }
}
