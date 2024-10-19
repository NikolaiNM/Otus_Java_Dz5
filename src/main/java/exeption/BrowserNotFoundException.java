package exeption;

public class BrowserNotFoundException extends RuntimeException {

    public BrowserNotFoundException(String browserName) {
        super(String.format("Бразер %s на поддерживается", browserName));
    }

}
