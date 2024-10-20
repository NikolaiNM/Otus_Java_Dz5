package pages;
import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


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

    // Метод для ввода текста в выбранный элемент
    public AbsBasePage enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text); // Вводим текст в найденный элемент
        return this; // Возвращаем текущий экземпляр для цепочного вызова
    }

    // Метод для выбора элемента в выпадающем списке по значению
    public AbsBasePage selectDropdownByValue(WebElement element, String value) {
        Select select = new Select(element); // Создаем объект Select
        select.selectByValue(value); // Выбор по значению
        return this; // Возвращаем экземпляр для цепочного вызова
    }
}
