package com.Pages;

import org.json.JSONObject;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Cart extends BaseTest{
	TestUtils utils = new TestUtils();
	JSONObject Checkoutdetails;
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"Checkout\"]/android.view.ViewGroup/android.widget.TextView") 
	private MobileElement CheckoutCart; 
	
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"F.Name\"]/android.view.ViewGroup/android.widget.TextView") 
	private MobileElement Firstname; 
	
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"L.Name\"]/android.view.ViewGroup/android.widget.TextView") 
	private MobileElement Lastname; 
	
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"PostalCode\"]/android.view.ViewGroup/android.widget.TextView") 
	private MobileElement PC; 
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"Confirm\"]/android.view.ViewGroup/android.widget.TextView") 
	private MobileElement ConfirmCheckout; 
	
	public void checkout(String FirstName, String LastName, String PostalCode) {
		
		
		click(CheckoutCart);
		Firstname.sendKeys(FirstName);
		Lastname.sendKeys(LastName);
		PC.sendKeys(PostalCode);
		click(ConfirmCheckout);
		
	}

}
