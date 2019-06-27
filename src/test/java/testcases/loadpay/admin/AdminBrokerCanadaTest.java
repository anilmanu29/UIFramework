package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;

import util.TestUtil;

public class AdminBrokerCanadaTest extends TestBase {
	AdminHomePage h;
	AdminLogin adminLoginObj;
	Select s;
	
	public static String depositAmount = "";

	public AdminBrokerCanadaTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {
		TestUtil.className = this.getClass().getName();
		initialization();
		h = new AdminHomePage();
		adminLoginObj = new AdminLogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "Switch to admin URL")
	public void Home() throws IOException, AWTException, InterruptedException {
		h.AdminURL();
		 Actions action = new Actions(driver);
			
			action.moveToElement(driver.findElement(By.xpath("//*[@id=\"___gatsby\"]/div/div[2]/div/div/div[2]/div[2]/div[4]/div[1]/span"))).build().perform();
			
			Thread.sleep(3000);
			
			driver.findElement(By.linkText("Community")).click();
			
			// Store all currently open tabs in tabs
			 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			 
			 
			 // Switch newly open Tab
			 driver.switchTo().window(tabs.get(0));
			 
			 // Perform some operation on Newly open tab
			 // Close newly open tab after performing some operations.
			 driver.close();
			 
			 // Switch to old(Parent) tab.
			 driver.switchTo().window(tabs.get(1));
			 adminLoginObj.adminLoginLink();

		}
	
	@Test(dataProvider = "getRevoltadminloginData", dependsOnMethods = "Home")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {
		adminLoginObj.adminUserPass(Username, pass);
		adminLoginObj.adminLogin();
		
	}



}


