package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String ProdName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();// chromedriver is added 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("anshika1+1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anshika@123!");
		driver.findElement(By.id("login")).click();
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products =	driver.findElements(By.cssSelector(".mb-3"));
	
    WebElement prod= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProdName)).findFirst().orElse(null);

    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));  
    // ng-animating
  wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
 
 /* Wait<WebDriver> wait1 =new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
		  .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);


		  WebElement foo = wait1.until(new Function<WebDriver, WebElement>() {
		      
		  	public WebElement apply(WebDriver driver) {
		  		
		        if(driver.findElement(By.cssSelector("[routerlink*='cart']")).isDisplayed())
		      	  
		        {
		      	  return driver.findElement(By.cssSelector("[routerlink*='cart']"));
		        }
		        
		        else
		      	  return null;
		        
		  	}	
		  	}); */
  
  Thread.sleep(3000);
 
  driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    
List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
Boolean match =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProdName)) ;   
Assert.assertTrue(match);

js.executeScript("window.scrollBy(0,1000)");
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));

Thread.sleep(3000);
driver.findElement(By.cssSelector(".totalRow button")).click();


WebElement ab = driver.findElement(By.cssSelector("[placeholder = 'Select Country']"));

Actions a = new Actions(driver);

a.sendKeys(ab, "india").build().perform();

wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

js.executeScript("window.scrollBy(0,1000)");

//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
Thread.sleep(3000);
driver.findElement(By.cssSelector(".action__submit")).click();

String message = driver.findElement(By.cssSelector(".hero-primary")).getText();

Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

driver.close();


	}

}
