package actions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PurchaseHatsTestSuite {
    private static WebDriver driver;

    @BeforeTest
    public void startUp() throws IOException {
        StartBrowser openConnection = new StartBrowser(driver);
        driver = openConnection.initializeBrowser();
    }

    @Test
    public void successfulPurchase() throws IOException, InterruptedException {
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println("SuccessfulPurchase Test Starts");

        StartBrowser system = new StartBrowser(driver);
        system.launchBrowser();

        HomePage homePage = new HomePage(driver);
        homePage.verifyLogoIsPresent();
        homePage.clearSearchBox();
        homePage.enterTextIntoSearchBox("hats for men");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.verifyResultsTextIsDisplayed();
        resultsPage.selectFirstHat();

        ProductPage product = new ProductPage(driver);
        double menHatPrice = product.getProductPrice();
        product.selectProductQuantity();
        product.clickAddToCart();
        product.openCart();

        CartPage cart = new CartPage(driver);
        cart.verifyMaleProductQuantity("two");
        double menHatSubtotal = cart.validateMensHatTotalAmount(menHatPrice);

        homePage.enterTextIntoSearchBox("hats for women");

        resultsPage.verifyResultsTextIsDisplayed();
        resultsPage.selectFirstHat();

        double femaleHatPrice = product.getProductPrice();
        product.clickAddToCart();
        product.openCart();

        cart.verifyFemaleProductQuantity();
        cart.validateSubTotalAmount(femaleHatPrice, menHatSubtotal);
        cart.reduceProductInCart();
        cart.verifyMaleProductQuantity("one");
        cart.validateFinalSubTotalAmount(femaleHatPrice, menHatSubtotal);

        System.out.println("Successful Purchase Test Passed");
    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
        System.out.println("Close Browser Successfully");
    }
}
