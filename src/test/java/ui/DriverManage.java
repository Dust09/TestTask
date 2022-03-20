package ui;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface DriverManage {
    void click(String webElement);
    void sendKeys(String webElement, String value);
    List<WebElement> getElements(String xpath);
    void driverGet(String url);
}
