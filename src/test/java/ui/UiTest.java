package ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pageObjects.DashBoardPage;
import ui.pageObjects.LoginPage;
import ui.pageObjects.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UiTest extends TestBase {
    private final LoginPage loginPage = new LoginPage();
    private final DashBoardPage dashBoardPage = new DashBoardPage();
    private final Users users = new Users();

    List<WebElement> dates = new ArrayList<>();
    List<String> notSortedData = new ArrayList<>();

    @Before
    public void initDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Given("Go to the site {string}")
    public void goToTheSite(String site){
       driverGet(site);
    }

    @When("user login {string} and password {string} and click login")
    @SneakyThrows
    public void login(String login, String password){
        sendKeys(loginPage.getLoginField(),login);
        sendKeys(loginPage.getPasswordField(),password);
        Thread.sleep(2000);
        click(loginPage.getSignInButton());

    }

    @Then("click on players table")
    public void clickOnPlayersTable(){
        click(dashBoardPage.getPlayersOnlineOrTotal());
        Assertions.assertEquals(17,getElements(users.getAllTables()).size());
    }

    @And("click on registration data column")
    @SneakyThrows
    public void clickOnDataColumn(){
        Thread.sleep(1000);
        click(users.getRegistrationDateColumn());
    }

    @And("check sorting is work")
    public void checkSortedIsWork(){
        dates = getElements(users.getAllDataInRegistrationColumn());

        for(WebElement obj:dates){
            notSortedData.add(obj.getText());
        }
        notSortedData = doSortedData(notSortedData);

        for(int i=0;i<dates.size();i++){
            Assertions.assertEquals(notSortedData.get(i),dates.get(i).getText());
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
