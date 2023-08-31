package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Util;

public class ProductPage {

    private final WebDriver driver;

    By productPriceWhole = By.xpath("(//span[starts-with(@class, 'a-price-whole')])[1]");
    By productPriceFraction = By.xpath("(//span[starts-with(@class, 'a-price-fraction')])[1]");
    By quantity = By.id("quantity");
    By quantityValue = By.xpath("//li[@aria-labelledby='quantity_1']");
    By addToCartBtn = By.id("add-to-cart-button");
    By cart = By.xpath("(//a[contains(text(), 'Go to Cart')])[1]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    Util util = new Util();

    public double getProductPrice(){
        WebElement whole = driver.findElement(productPriceWhole);
        WebElement fraction = driver.findElement(productPriceFraction);
        String wholeString = whole.getText();
        String fractionString = fraction.getText();
        double price = util.concatenatePricesValues(wholeString, fractionString);
        System.out.println("Price is displayed successfully. Price -> " + price);
        return price;
    }

    public void selectProductQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement quantityDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(quantity));
        Actions actions = new Actions(driver);
        actions.moveToElement(quantityDropdown).click().perform();
        WebElement selectQty = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityValue));
        actions.moveToElement(selectQty).click().perform();
        System.out.println("Quantity 2 selected successfully");
    }

    public void clickAddToCart() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(addToCartBtn).click();
        System.out.println("Add to cart clicked successfully");
    }

    public void openCart(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cart)).click();
        System.out.println("Cart opened successfully");
    }

}