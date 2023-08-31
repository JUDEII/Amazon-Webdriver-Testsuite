package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class HomePage {

    private final WebDriver driver;

    By logoImage = By.id("nav-logo-sprites");
    By searchBox = By.id("twotabsearchtextbox");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyLogoIsPresent() {
        boolean isPresent = driver.findElement(logoImage).isDisplayed();
        assertTrue(isPresent);
        System.out.println("Amazon logo is displayed");
    }

    public void clearSearchBox() {
        driver.findElement(searchBox).clear();
        System.out.println("search box cleared successfully");
    }

    public void enterTextIntoSearchBox(String text) throws InterruptedException {
        Thread.sleep(5000);
        WebElement search = driver.findElement(searchBox);
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
        System.out.println("search text successfully entered");
    }

}
