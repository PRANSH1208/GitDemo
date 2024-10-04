package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	
	@Test(dataProvider="getData", groups = {"Purchase"})
	//public void submitOrder(String email, String Password, String productName) throws IOException, InterruptedException
	//{
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
		//LandingPage landingPage =launchApplication();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//ProductCatalogue productCatalogue = landingPage.loginApplication(email, Password);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		js.executeScript("window.scrollBy(0,1000)");
		CheckoutPage CheckoutPage = cartPage.goToCheckout();
		CheckoutPage.selectCountry("india");
		//js.executeScript("window.scrollBy(0,1000)");
		ConfirmationPage ConfirmationPage = CheckoutPage.submitOrder();
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}
	
	// to verify ZARA coat 3 is displaying in Orders pg
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika1+1@gmail.com", "Anshika@123!");
	OrdersPage ordersPage =	productCatalogue.goToOrdersPage();
	Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	

	@DataProvider
	public Object[][] getData() throws IOException
	
	{		
	List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	/*{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "anshika1+1@gmail.com");
		map.put("password", "Anshika@123!");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "shetty1+1@gmail.com");
		map1.put("password", "IamKing@000");
		map1.put("productName", "ADIDAS ORIGINAL");
		
	}*/
	
	/*@DataProvider
	public Object[][] getData()
	
	 {
		return new Object [][] {{"anshika1+1@gmail.com", "Anshika@123!", "ZARA COAT 3"}, {"shetty1+1@gmail.com", "IamKing@000", "ADIDAS ORIGINAL"}};
	}*/
	
	
}
