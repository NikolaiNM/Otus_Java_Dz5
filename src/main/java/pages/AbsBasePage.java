package pages;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbsBasePage extends AbsCommon {

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    private String BASE_URL = System.getProperty("base.url", "https://otus.home.kartushin.su/");

    public void open(String path) {
        driver.get(BASE_URL + path);
    }

    // Метод для поиска элемента по CSS-селектору
    public WebElement findByCssSelector(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector)); // Возвращаем найденный элемент
    }

    // Метод для клика по элементу
    public AbsBasePage clickElement(String cssSelector) {
        WebElement element = findByCssSelector(cssSelector); // Находим элемент по CSS-селектору
        element.click();  // Кликаем по элементу
        return this;  // Возвращаем текущий экземпляр для цепочного вызова
    }

    // Метод для ввода текста в выбранный элемент
    public AbsBasePage enterText(WebElement element, String text) {
        element.sendKeys(text); // Вводим текст в найденный элемент
        return this; // Возвращаем текущий экземпляр для цепочного вызова
    }
}
