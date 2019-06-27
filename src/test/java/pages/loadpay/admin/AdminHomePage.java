package pages.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import java.util.ArrayList;
import java.util.List;

public class AdminHomePage extends TestBase {
	static ArrayList<String> tabs;
	
	public AdminHomePage() throws IOException {
		PageFactory.initElements(driver, this);

	}

	public void AdminURL() throws AWTException, InterruptedException {

		driver.get(super.getProperties().getProperty("RevoltURL"));

	}
	
	public static void SwitchtoTab(int index) throws InterruptedException {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}
}
