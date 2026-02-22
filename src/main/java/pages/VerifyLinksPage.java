package pages;

import org.openqa.selenium.*;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class VerifyLinksPage {

    WebDriver driver;
    List<WebElement> links;

    public VerifyLinksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void extractLinks() {

        links = driver.findElements(By.xpath("//script[@src]"));
        System.out.println("Total links found: " + links.size());
    }

    public void validateLinks() {

        for (WebElement element : links) {

            String url = element.getAttribute("src");

            if (url == null || url.isEmpty()) continue;

            try {

                Response res = given()
                        .when()
                        .get(url);

                int statusCode = res.getStatusCode();

                if (statusCode >= 200 && statusCode < 400) {
                    System.out.println(url + " --> Working (" + statusCode + ")");
                } else {
                    System.out.println(url + " --> Broken (" + statusCode + ")");
                }

            } catch (Exception e) {
                System.out.println(url + " --> Broken (Exception: " + e.getMessage() + ")");
            }
        }
    }
}