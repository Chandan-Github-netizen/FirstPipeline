package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.VerifyLinksPage;

public class VerifyLinks {

    WebDriver driver;
    VerifyLinksPage verifyLinksPage;

    @Given("the user navigates to website")
    public void the_user_navigates_to_website() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Important for pipeline

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://www.screener.in/");

        verifyLinksPage = new VerifyLinksPage(driver);

        System.out.println("Website opened successfully");
    }

    @Then("the user extracts the links from the page")
    public void the_user_extracts_the_links_from_the_page() {

        verifyLinksPage.extractLinks();
    }

    @And("validates the link successfully")
    public void validates_the_link_successfully() {

        verifyLinksPage.validateLinks();
        driver.quit();
    }

    @And("print regression is running")
    public void regression() {
        System.out.println("print regression is running");
    }
}