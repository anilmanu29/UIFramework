package pages.revolut;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class AdminHomePage extends TestBase {
	static ArrayList<String> tabs;

	public AdminHomePage() throws IOException {
		PageFactory.initElements(driver, this);

	}

	public void AdminURL() throws AWTException, InterruptedException {

		driver.get(super.getProperties().getProperty("RevolutURL"));

	}

	public static void SwitchtoTab(int index) throws InterruptedException {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}
}
