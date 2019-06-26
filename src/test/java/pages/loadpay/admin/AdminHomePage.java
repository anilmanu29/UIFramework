package pages.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class AdminHomePage extends TestBase {
	public AdminHomePage() throws IOException {
		PageFactory.initElements(driver, this);

	}

	public void AdminURL() throws AWTException, InterruptedException {

		driver.get(super.getProperties().getProperty("RevoltURL"));

	}
}
