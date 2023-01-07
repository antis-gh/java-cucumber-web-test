package stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.GoogleLandingPage;
import pages.GoogleSearchResultsPage;


public class Stepdefs {
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROME_DRIVER_PATH = "./driver/chromedriver";
    protected WebDriver driver;
    protected WebElement searchBox;

    @Given("I go to google")
    public void i_go_to_google() {

        driver.get("http://www.google.com/xhtml");
        WebElement acceptAllButton = driver.findElement(By.xpath("//*[text()='Accept all']"));
        acceptAllButton.click();
    }

    @When("I search {string}")
    public void i_search(String searchKeyword) {
        System.err.println(String.format("KeyWord: %s", searchKeyword));
        GoogleLandingPage page = PageFactory.initElements(driver, GoogleLandingPage.class);
        page.searchFor(searchKeyword);
    }

    @Then("I should see {string}")
    public void i_should_see(String searchKeyword) {
        GoogleSearchResultsPage page = PageFactory.initElements(driver, GoogleSearchResultsPage.class);
        page.checkSearchResult(searchKeyword);
    }

    @Before
    public void beforeScenario(){
        System.out.println("Open browser");
        System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_PATH);

        final ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.setCapability("setJavascriptEnabled", "true");

        driver = new ChromeDriver(chromeOptions);
    }
	
	@After
    public void afterScenario(){
        System.out.println("Close browser");
        driver.quit();
    }
}