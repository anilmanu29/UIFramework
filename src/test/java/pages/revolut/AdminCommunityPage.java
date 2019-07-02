package pages.revolut;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminCommunityPage extends TestBase {
	Select s;
	JavascriptExecutor js;

	@FindBy(xpath = "//*[@id=\"ember6\"]/header/div/div/div[2]/span/button[2]/span")
	WebElement LoginLink;

	public @FindBy(id = "login-account-name") WebElement username;

	public @FindBy(id = "login-account-password") WebElement password;

	public @FindBy(xpath = "/html/body/section/div/div[4]/div/div/div/div[3]/div[2]/button[1]/span") WebElement loginbutton;

	@FindBy(xpath = "//*[@id=\"search-button\"]")
	WebElement searchTopic;

	@FindBy(xpath = "//*[@id=\"search-term\"]")
	WebElement searchOption;

	@FindBy(xpath = "//*[@id=\"ember6\"]/header/div/div/div[2]/div/div/div/div/div[3]/div/div/ul/li[1]/a")
	WebElement dropdownlink;

	@FindBy(css = ".onebox-body > h3:nth-child(2) > a:nth-child(1)")
	WebElement weGotaBankingLicenceLink;

	@FindBy(css = ".d-icon-bars")
	WebElement hamburgermenu;

	@FindBy(linkText = "Keyboard Shortcuts")
	WebElement keyboardShortcutsLink;

	@FindBy(css = ".styles__StyledSelectedCountry-ps6zp6-0 > div:nth-child(1)")
	WebElement countryflaglink;

	@FindBy(xpath = "//*[@id=\"___gatsby\"]/div/div[4]/div/div[2]/div[2]/div[2]/div[4]/div[1]")
	WebElement unitedstateslink;

	public AdminCommunityPage() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void adminLoginLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(LoginLink));
		LoginLink.click();
		wait.until(ExpectedConditions.elementToBeClickable(LoginLink));
	}

	public void adminUserPass(String Username, String pass) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(username));
		username.sendKeys(Username);
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.sendKeys(pass);
	}

	public void adminLoginButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
		js.executeScript("arguments[0].click();", loginbutton);

	}

	public void clicksearchTopic() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchTopic));
		searchTopic.click();
		// js.executeScript("arguments[0].click();", searchTopic);

	}

	public void EntersearchOption(String searchvalue) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(searchOption));
		searchOption.click();
		searchOption.clear();
		searchOption.sendKeys(searchvalue);
	}

	public void clickDropdownLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(dropdownlink));
		dropdownlink.click();
	}

	public void clickWeGotaBankingLicenceLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(weGotaBankingLicenceLink));
		weGotaBankingLicenceLink.click();
	}

	public void clickHamburgermenu() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(hamburgermenu));
		hamburgermenu.click();
	}

	public void clickKeyboardShortcutsLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(keyboardShortcutsLink));
		keyboardShortcutsLink.click();
	}

	public void clickCountryFlagLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(countryflaglink));
		countryflaglink.click();
	}

	public void ClickUnitedStatesLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(unitedstateslink));
		unitedstateslink.click();
	}

	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return username;
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return password;
	}

	/**
	 * @return the loginBtn
	 */
	public WebElement getLoginBtn() {
		return loginbutton;
	}

	/**
	 * @return the logOut
	 */

}