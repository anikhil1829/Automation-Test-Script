package ecom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class flipkartOperations {
	public class FlipkartTest {
	    private WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        System.setProperty("webdriver.edge.driver", "G:\\msedgedriver.exe");
	        driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.flipkart.com/search");
	        
	        
	    }
	    @Test
	    public void testSearchProduct() {
	        // Find and enter search keyword
	        WebElement searchInput = driver.findElement(By.name("q"));
	        searchInput.sendKeys("I phone");
	        searchInput.sendKeys(Keys.ENTER);
            
//	        List<WebElement> links = driver.findElements(By.xpath("//div[@class='_4rR01T']//a"));
//	        for (WebElement link : links) {
//	            String linkText = link.getText();
//	            System.out.println(linkText);
	    
	     // Click on the first search result
	        WebElement firstSearchResult = driver.findElement(By.xpath("//div[@id='container']//div[3]/div[1]/div[2]/div[2]//div/a/div[2]/div[1]/div[1]"));
	        firstSearchResult.click();
	        
	        // Add the item to the cart
	        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
	        addToCartButton.click();
	     // Wait for the cart count to update
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        // Verify that the item was added to the cart
	        WebElement cartCount = driver.findElement(By.xpath("//div[@id='container']/div/div[2]/div/div/div[1]/div/div[1]/div/div/a[1]/div"));
	        String cartItemCount = cartCount.getText();
	        if (cartItemCount.equals("1")) {
	            System.out.println("Item successfully added to the cart!");
	        } else {
	            System.out.println("Failed to add item to the cart.");
	        }
	        
	        //Updating number of product from cart 
	        WebElement updateCart = driver.findElement(By.xpath("//div[@id='container']//div[2]//div[1]//div[3]//div[3]/div[1]/div/button[2]"));
	        updateCart.click();
	        
	        //Remove the product from cart
	        WebElement removeProduct = driver.findElement(By.xpath("//div[@class='nZz3kj _1hNI6F']/div/div[2]"));
	        removeProduct.click();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.
					presenceOfElementLocated(
						By.xpath("//div[@id='container']//div[1]/div/div[1]")));
	        
	        WebElement removeButton = driver.findElement(By.xpath("//div[@id='container']/div/div[1]/div/div[3]/div/div[2]"));
	        removeButton.click();
	        
	        //Assert that the product is removed successfully from cart
            WebElement missingItemText = driver.findElement(By.xpath("//div[@id=\"container\"]//div[2]//div[2]//div[1]"));
	        Assert.assertEquals(missingItemText.getText(), "Missing Cart items?");

	        // Close the browser
	        driver.quit();

	        }
	        
	        
	    }
	    
	}




