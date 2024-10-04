package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage ConfirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
	
		landingPage=	launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Given_Logged_in_with_username_and_Password(String username, String password)
	{
		productCatalogue= landingPage.loginApplication(username, password);
	}

	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	
	@When("^Checkout (.+) are submit the order$")
	public void Checkout_are_submit_the_order(String productName) throws InterruptedException
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		//js.executeScript("window.scrollBy(0,1000)");
		CheckoutPage CheckoutPage = cartPage.goToCheckout();
		CheckoutPage.selectCountry("india");
		//js.executeScript("window.scrollBy(0,1000)");
		ConfirmationPage = CheckoutPage.submitOrder();
	
	}
	
	
	
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string)
	{
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	
	 @Then("^\"([^\"]*)\" message is displayed$")
	 public void something_message_is_displayed(String strArg1)
	 {
		 Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		 driver.close();
	 }
	
}
