package ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.SneakyThrows;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(features = "/src/test/java/resources/features", glue = {"ui"})
public abstract class TestBase implements DriverManage{
    public static WebDriver driver;

    @Override
    public void click(String webElement){
        driver.findElement(By.xpath(webElement)).click();
    }

    @Override
    public void sendKeys(String webElement, String value){
        driver.findElement(By.xpath(webElement)).sendKeys(value);
    }

    @Override
    public List<WebElement> getElements(String xpath){
        return driver.findElements(By.xpath(xpath));
    }

    @Override
    public void driverGet(String url){
        driver.get(url);
    }

    @SneakyThrows
    public final List<String> doSortedData(List<String> notSortedList){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates = new ArrayList<>();
        for(String l:notSortedList){
            if(l!=null || !" ".contains(l)){
                dates.add(dateFormat.parse(l));
            }
        }
        dates.sort(Comparator.comparing(Date::getTime));
        List<String> sortedData = new ArrayList<>();
        for (Date d:dates){
            sortedData.add(dateFormat.format(d));
        }
        return sortedData;
    }
}
