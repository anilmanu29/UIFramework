package util;
/*************************************** PURPOSE **********************************

 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.

 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 

 */

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {

	TestUtil testUtilObj = new TestUtil();
	String elementLogMsg = "Element value: ";
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		log.info("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		log.info("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element) {
		log.info("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element) {
		log.info("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		log.info("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		log.info("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		log.info("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		log.info("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		log.info("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		log.info("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		log.info("Exception occured: " + error);
		try {
			testUtilObj.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			log.info(e);
		}
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
		log.info(elementLogMsg + script);
	}

	public void afterScript(String script, WebDriver driver) {
		log.info(elementLogMsg + script);
	}

	public void beforeAlertAccept(WebDriver driver) {
		log.info(elementLogMsg + driver);

	}

	public void afterAlertAccept(WebDriver driver) {
		log.info(elementLogMsg + driver);

	}

	public void afterAlertDismiss(WebDriver driver) {
		log.info(elementLogMsg + driver);

	}

	public void beforeAlertDismiss(WebDriver driver) {
		log.info(elementLogMsg + driver);

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		log.info(elementLogMsg + driver);

	}

	public void afterNavigateRefresh(WebDriver driver) {
		log.info(elementLogMsg + driver);

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		log.info(elementLogMsg + element);

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		log.info(elementLogMsg + element);

	}

}