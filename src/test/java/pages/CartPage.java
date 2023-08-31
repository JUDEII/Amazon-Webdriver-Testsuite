package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Util;

import static org.testng.Assert.assertEquals;

public class CartPage {

    private final WebDriver driver;
    private final String MENS_HAT_QUANTITY = "2";
    private final String FEMALE_HAT_QUANTITY = "1";
    private final String NEW_MENS_HAT_QUANTITY = "1";

    By maleQuantityValue = By.xpath("//span[@class='a-dropdown-prompt']");
    By quantityPrice = By.xpath("//span[@id='sc-subtotal-amount-activecart']/descendant::span");
    By femaleQuantityValue = By.xpath("(//span[@class='a-dropdown-prompt'])[1]");
    By quantity = By.xpath("(//select[@id='quantity'])[2]");
    By quantityValue = By.xpath("//li[@aria-labelledby='quantity_1']");
    By newMaleQuantityValue = By.xpath("(//span[@class='a-dropdown-prompt'])[2]");



    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    Util util = new Util();

    public void verifyMaleProductQuantity(String value){
        By element = null;
        element = value.equals("two") ? maleQuantityValue : newMaleQuantityValue;
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        String qtyValue = driver.findElement(element).getText();
        if (value.equals("two")) {
            assertEquals(qtyValue, MENS_HAT_QUANTITY);
        } else {
            assertEquals(qtyValue, NEW_MENS_HAT_QUANTITY);
        }
        System.out.println("product quantity displayed successfully. Quantity -> " + value);
    }

    public void verifyFemaleProductQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(femaleQuantityValue));
        String qtyValue = driver.findElement(femaleQuantityValue).getText();
        assertEquals(qtyValue, FEMALE_HAT_QUANTITY);
        System.out.println("product quantity displayed successfully. Quantity -> " + FEMALE_HAT_QUANTITY);
    }

    public double validateMensHatTotalAmount(double menHatPrice) {
        double price = menHatPrice * Double.parseDouble(MENS_HAT_QUANTITY);
        double subTotal = util.convertStringToNumber(driver.findElement(quantityPrice).getText());
        assertEquals(price, subTotal);
        System.out.println("Cart subtotal is correct. price -> " + price + " , subtotal -> " + subTotal);
        return price;
    }

    public void validateSubTotalAmount(double femaleHatPrice, double menHatSubtotal) {
        double finalPrice = femaleHatPrice + menHatSubtotal;
        finalPrice = Math.round(finalPrice * 100.0) / 100.0;
        double subTotal = util.convertStringToNumber(driver.findElement(quantityPrice).getText());
        assertEquals(finalPrice, subTotal);
        System.out.println("Cart subtotal is correct. finalPrice -> " + finalPrice + " , subtotal -> " + subTotal);
    }

    public void reduceProductInCart(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement quantityDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(quantity));
        Actions actions = new Actions(driver);
        actions.moveToElement(quantityDropdown).click().perform();
        WebElement selectQty = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityValue));
        actions.moveToElement(selectQty).click().perform();
        System.out.println("Quantity 1 selected successfully");
    }

    public void validateFinalSubTotalAmount(double femaleHatPrice, double menHatSubtotal) throws InterruptedException {
        Thread.sleep(5000);
        double finalSubtotal = menHatSubtotal / Double.parseDouble(MENS_HAT_QUANTITY);
        finalSubtotal += femaleHatPrice;
        double subTotal = util.convertStringToNumber(driver.findElement(quantityPrice).getText());
        assertEquals(finalSubtotal, subTotal);
        System.out.println("Cart subtotal is correct. finalPrice -> " + finalSubtotal + " , subtotal -> " + subTotal);
    }

}
