package pages;
import common.AbsCommon;
import enums.registrationform.InputField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public abstract class AbsBasePage extends AbsCommon {

    private final String BASE_URL;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        this.BASE_URL = System.getProperty("base.url", "https://otus.home.kartushin.su/"); // Инициализируем в конструкторе
    }

    public void open(String path) {
        driver.get(BASE_URL + path);
    }

    // Метод для поиска элемента по CSS-селектору
    public WebElement findByCssSelector(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector)); // Возвращаем найденный элемент
    }

    // Метод для ввода текста в выбранный элемент
    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text); // Вводим текст в найденный элемент
    }

    // Метод для выбора элемента в выпадающем списке по значению
    public void selectDropdownByValue(WebElement element, String value) {
        Select select = new Select(element); // Создаем объект Select
        select.selectByValue(value); // Выбор по значению
    }

    public void enterFieldData(InputField field, String value) {
        WebElement element = findByCssSelector(field.getSelector());
        enterText(element, value);
        logger.info("{} введено: {}", field.name(), value);
    }
}
