package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import util.TestUtil;

public class AdminBrokerTest extends TestBase {
	AdminHomePage h;
	AdminLogin a;
	Select s;
	String CreditAmount = "";

	public AdminBrokerTest() {
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

	@Test(description = "Get Credit Amount", dataProvider = "getExtendedCreditAmount")
	public void getExtendedCreditAmountTest(String Amount) throws IOException, AWTException, InterruptedException {
		CreditAmount = Amount;
	}

	@Test(description = "Switch to admin URL", dependsOnMethods = "getExtendedCreditAmountTest")
	public void Home() throws IOException, AWTException, InterruptedException {
		h.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "Home")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {
		a.adminUserPass(Username, pass);

		a.adminLogin();

		a.ClickOnCustomersTab();

		a.Uncheck_Factor();

		

		a.ClickOnSearchButton();

		a.DoubleClickID();

		a.StatusIDDropDown();

		a.UpdateButton();

		a.ClickOnCreditTab();

		a.EnterExtendedCredit(CreditAmount);

		a.ClickOnCreditSubmitButton();

		a.AdminLogOut();

	}

}
