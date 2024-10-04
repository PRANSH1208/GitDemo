package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
	
		//String productName = "ZARA COAT 3";
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		landingPage.loginApplication("anshi@gmail.com", "Anshika@123!");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
	
		String productName = "ZARA COAT 3";
		// LandingPage landingPage =launchApplication();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika1+1@gmail.com","Anshika@123!");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		

	}

}
