import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceDemo {
    private static final String URL = "https://www.saucedemo.com/";
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();

    }

    @Test
    public void loginTest() {
        WebElement inputUserName = driver.findElement(By.id("user-name"));
        inputUserName.clear();
        inputUserName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.name("password"));
        inputPassword.clear();
        inputPassword.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        addToCartButton.click();
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-bike-light"));
        Assert.assertTrue(removeButton.isDisplayed());
        WebElement shoppingCartButton = driver.findElement(By.cssSelector("a[class=shopping_cart_link]"));
        shoppingCartButton.click();
        WebElement shoppingCartTitle = driver.findElement(By.cssSelector("span[class = shopping_cart_badge"));
        Assert.assertTrue(shoppingCartTitle.isDisplayed());
        WebElement itemName = driver.findElement(By.xpath("//div[text() = 'Sauce Labs Bike Light']"));
        String expectedItemName = "Sauce Labs Bike Light";
        Assert.assertEquals(itemName.getText(), expectedItemName);
        WebElement itemPrice = driver.findElement(By.cssSelector(".inventory_item_price"));
        Assert.assertEquals("$9.99", itemPrice.getText());


    }
}

