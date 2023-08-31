package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResultsPage {

    private final WebDriver driver;

    By resultsHeader = By.xpath("//span[contains(text(),'Results')]");
    By firstHat = By.xpath("(//img[starts-with(@class, 's-image')])[1]");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyResultsTextIsDisplayed() {
        String resultHeader = "Results";
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsHeader));
        String message = driver.findElement(resultsHeader).getText();
        boolean isPresent = element.isDisplayed();
        assertTrue(isPresent);
        System.out.println("Results header text is displayed successfully");
        assertEquals(message, resultHeader);
    }

    public void selectFirstHat() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstHat)).click();
        System.out.println("First hat is *CLICKED* successfully");
    }

}

