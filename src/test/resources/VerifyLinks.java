package test.java.testAPI;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.restassured.response.Response;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static io.restassured.RestAssured.given;

public class VerifyLinks {

    WebDriver driver;

//    @FindBy(xpath = "//a[@href]")
//    private WebElement links;

    // Constructor
    public VerifyLinks() {
        //PageFactory.initElements(driver, this);
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");

        System.out.println("***** TEST IS RUNNING *****");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Google homepage
        driver.get("https://www.screener.in/");
    }

    public void validateLink() {

        // Get all links on page
        List<WebElement> arr = driver.findElements(By.xpath("//script[@src]"));

        System.out.println("Total links found: " + arr.size());

        for (WebElement url : arr) {

            String link=url.getAttribute("src");
            try {
                Response res = given().when().get(link);  // Using HEAD (faster)

                int statusCode = res.getStatusCode();

                if (statusCode >= 200 && statusCode < 400) {
                    System.out.println(link + " --> Working (" + statusCode + ")");
                } else {
                    System.out.println(link + " --> Broken (" + statusCode + ")");
                }

            } catch (Exception e) {
                System.out.println(link + " --> Broken (Exception: " + e.getMessage() + ")");
            }
        }

        driver.quit();
    }

    public static void main(String[] args) {
        VerifyLinks obj = new VerifyLinks();
        obj.validateLink();
    }
}
