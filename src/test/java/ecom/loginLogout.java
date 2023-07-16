package ecom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginLogout {
	public class AmazonTest {
	    private WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        System.setProperty("webdriver.edge.driver", "G:\\msedgedriver.exe");
	        driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://signin.ebay.com/");
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }

	    @Test
	    public void testLoginAndLogout() {
	        // Find and click on the sign in button
	        WebElement signInButton = driver.findElement(By.id("userid"));
	        signInButton.sendKeys("your_username");
	        WebElement continueButton = driver.findElement(By.id("signin-continue-btn"));
	        continueButton.click();
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.
					presenceOfElementLocated(
						By.id("welcome-msg")));


	        // Enter password and click on the sign in button
	        WebElement passwordInput = driver.findElement(By.id("pass"));
	        passwordInput.sendKeys("your_pass");;
	        WebElement signInSubmitButton = driver.findElement(By.id("sgnBt"));
	        signInSubmitButton.click();

	        // Assert that the user is logged in successfully
	        WebElement loggedInUsername = driver.findElement(By.id("gh-p-1"));
	        Assert.assertEquals(loggedInUsername.getText(), "Daily Deals");


	        // Find and click on the sign out button
	        WebElement signOutButton = driver.findElement(By.id("gh-ug"));
	        signOutButton.click();
	        wait.until(ExpectedConditions.
					presenceOfElementLocated(
						By.id("gh-un")));
	        WebElement signOutLink = driver.findElement(By.id("gh-uo"));
	        signOutLink.click();

	        // Assert that the user is logged out successfully
	        WebElement signInText = driver.findElement(By.id("gh-ug"));
	        Assert.assertEquals(signInText.getText(), "Sign-In");
	    }
	}

}
