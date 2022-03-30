package com.qa.tests;

import com.Pages.Cart;
import com.Pages.LoginPage;
import com.Pages.ProductsPage;
import com.qa.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LoginTests extends BaseTest {
	LoginPage loginPage;
	ProductsPage productsPage;
	InputStream details;
	JSONObject loginUsers;
	Cart cart;

	@BeforeClass
	public void beforeClass() throws Exception {
		
		
		details = new FileInputStream("/Users/riyaanghosh/eclipse-workspace/MyTDDProject/src/test/resources/Data/data.json");
		JSONTokener jsonToken = new JSONTokener(details);
		loginUsers = new JSONObject(jsonToken);
		closeApp();
		launchApp();
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		loginPage = new LoginPage();
	}

	
	@Test
	public void successfulLogin() {
		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		productsPage = loginPage.pressLoginBtn();

		String actualProductTitle = productsPage.getTitle();
		String expectedProductTitle = "PRODUCTS";

		Assert.assertEquals(actualProductTitle, expectedProductTitle);
	}
	public void addRequiredItemToCart() {
		productsPage.AddToCart();
		
	}
	public void checkoutCart(String FN,String LN,String Postal) {
		cart.checkout(FN, LN, Postal);
	}
	public void verifyConfirmationMessage() {
		String message ="Success";
		driver.getTitle().contentEquals(message);
	}

	@AfterClass
	public void afterClass() throws IOException {
		details.close();
	}

	@AfterMethod
	public void afterMethod() {
	}
}
