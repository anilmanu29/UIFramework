package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

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
	AdminLogin a;
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
		a = new AdminLogin();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "Switch to admin URL")
	public void Home() throws IOException, AWTException, InterruptedException {
		h.AdminURL();
		 Actions action = new Actions(driver);
			
			action.moveToElement(driver.findElement(By.xpath("//*[@id=\"___gatsby\"]/div/div[2]/div/div/div[2]/div[2]/div[4]/div[1]/span"))).build().perform();
			
			Thread.sleep(3000);
			
			driver.findElement(By.linkText("Community")).click();
	}

//	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "Home")
//	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {
//		wait.until(ExpectedConditions.elementToBeClickable(a.getUserName()));
//		a.adminUserPass(Username, pass);
//
//		a.adminLogin();
//
//		a.ClickOnCustomersTab();
//
//		a.ClickOnSearchButton();
//
//		a.DoubleClickID();
//
//		a.StatusIDDropDown();
//
//		a.UpdateButton();
//
//		Thread.sleep(2000);
//
//		a.ClickOnCreditTab();
//
//		a.EnterExtendedCredit("999999");
//
//		a.ClickOnCreditSubmitButton();
//		Thread.sleep(3000);
//
//		// go to banking tab and capture deposit amount
//		WebElement adminCustomerBankingTab = driver.findElement(By.xpath("//a[contains(text(),'Banking')]"));
//		adminCustomerBankingTab.click();
//
//		wait.until(ExpectedConditions.elementToBeClickable(adminCustomerBankingTab));
//		Thread.sleep(10000);
//
//		WebElement adminCustomerDepositAmount = driver.findElement(By.xpath(
//				"//*[@id=\"angularScope\"]/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/p[9]/span"));
//		depositAmount = adminCustomerDepositAmount.getText();
//		depositAmount = depositAmount.substring(depositAmount.length() - 2, depositAmount.length());
//		// depositAmount = "0" + depositAmount;
//		log.info("Captured deposit amount: " + depositAmount);
//
//		wait.until(ExpectedConditions.elementToBeClickable(a.getLogOut()));
//		a.AdminLogOut();
//
//	}

}
