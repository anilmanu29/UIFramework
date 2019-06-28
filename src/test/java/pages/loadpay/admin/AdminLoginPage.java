package pages.loadpay.admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class AdminLoginPage extends TestBase {
	Select s;
	JavascriptExecutor js;

	@FindBy(xpath = "//*[@id=\"ember6\"]/header/div/div/div[2]/span/button[2]/span")
	WebElement LoginLink;
	
	
	@FindBy(id = "login-account-name")
	WebElement username;
	
	@FindBy(id = "login-account-password")
	WebElement password;
	
	@FindBy(xpath = "/html/body/section/div/div[4]/div/div/div/div[3]/div[2]/button[1]/span")
	WebElement loginbutton;
	
	//----------------------------------------------------------------

	@FindBy(xpath = "html/body/div[1]/div/div/div[2]/form/div[4]/input")
	WebElement loginBtn;

	@FindBy(id = "btnEditPaymentTerms")
	WebElement selectEditLessThan14days;

	@FindBy(id = "lessThan14Days")
	WebElement selectLessThan14Days;

	@FindBy(id = "btnUpdatePaymentTerms")
	WebElement submitLessThan14Days;

	@FindBy(xpath = "//a[@href='/Account/LogOff']")
	WebElement logOut;

	@FindBy(xpath = "//a[@href='#/Customers']")
	public WebElement CustomerTab;

	@FindBy(xpath = ".//*[@id='PMNEnrolled']")
	public WebElement uncheckpaymennow;

	@FindBy(xpath = "//*[@id='updatebtnPayByInvoice']")
	WebElement click_updatebtnPayByInvoice;

	@FindBy(xpath = (".//*[@id='PMNEnrolled']"))
	WebElement enrollpaymenow;

	@FindBy(xpath = "//a[contains(text(), 'Delay Debit')]")
	WebElement delaydebit;

	@FindBy(xpath = ".//*[@id='btnEditDelayDebit']")
	WebElement EditDelayDebit;

	@FindBy(xpath = ("//a[contains(text(),'Credit')]"))
	WebElement CreditTab;

	@FindBy(xpath = (".//*[@id='angularScope']/div[3]/div/div/div[2]/button"))
	WebElement CloseButon;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[6]/div[2]/div/div/div[3]/div[2]"))
	WebElement verifysystemnote;
	
	@FindBy(css = "div[title='SYSTEM GENERATED NOTE: PAYMENT SETTINGS MODIFIED, \"Enable Payment Terms for Less Than 14 Days\" - ENABLED']")
	WebElement verifySystemNoteLessThan14DaysEnabled;
	
	@FindBy(css = "div[title='SYSTEM GENERATED NOTE: PAYMENT SETTINGS MODIFIED, \"Enable Payment Terms for Less Than 14 Days\" - DISABLED']" )
	WebElement verifySystemNoteLessThan14DaysDisabled;
	

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[5]")
	public WebElement lnk_AdminPayMeNow;

	@FindBy(xpath = (".//*[@id='greaterThan45daysId']"))
	WebElement selectgreaterThan45daysId;

	@FindBy(xpath = (".//*[@id='btnEditPaymentTermsGreaterThan45Days']"))
	WebElement Paymnttermgraterthan45days;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[14]/div/div/table/tbody/tr[1]/td[3]/button"))
	WebElement forwardfile;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[13]/div/div/table/tbody/tr[1]/td[3]/button"))
	WebElement carrierforwardfile;

	@FindBy(xpath = (".//*[@id='btnUpdatePaymentTermsGreaterThan45Days']"))
	WebElement Clickpaymentterm45Submit;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[6]"))
	WebElement clickNotes;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[14]/div/div/table/tbody/tr[1]/td[3]/a")
	public WebElement paymnt_Historydownload;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[13]/div/div/table/tbody/tr[1]/td[3]/a")
	public WebElement paymnt_carrierHistorydownload;

	@FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	public WebElement Update_paymenow;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[13]")
	WebElement PaymentHistory;

	@FindBy(id = "delayDebitId")
	WebElement delayedDebitStatusSelector;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[7]")
	WebElement PaymentTerms;

	@FindBy(xpath = ".//*[@id='angularScope']/div[3]/div/div/div[2]/button[2]")
	WebElement unenrolldelaydebit;

	@FindBy(css = "a[ng-class^='!Buttons.CustomerNotes']")
	WebElement PayMeNowTm;

	@FindBy(css = "a[ng-class^='!Buttons.PaymentHistory']")
	WebElement carrierPaymentHistory;

	@FindBy(css = "a[ng-class^='!Buttons.Banking']")
	WebElement AdminBanking;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr/td[1]/a")
	WebElement CustomerId;

	@FindBy(xpath = ".//*[@id='PayByInvoieStatus']")
	public WebElement select_PayByInvoieStatus;

	@FindBy(xpath = ".//*[@id='searchKeyword']")
	public WebElement search;

	@FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	public WebElement update_btn;

	@FindBy(id = "btnUpdateDelayDebit")
	public WebElement UpdateDelayDebit;

	@FindBy(xpath = ".//*[@id='angularScope']/div[3]/div/div/div[2]/button[2]")
	WebElement click_AdminResetpwdConfirm;

	@FindBy(xpath = ".//*[@id='formCustomerNote']/div[2]/div/div/button")
	WebElement closenotesbutton;

	@FindBy(xpath = ".//*[@id='angularScope']/div[3]/div/div/div[2]/button[2]")
	WebElement okbtn;

	@FindBy(xpath = "//input[@value='Search']") public WebElement ClickonSearchButton;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/img") 	WebElement loadingSpinner;

	public @FindBy(xpath = ".//*[@id='emailTo']") WebElement emailTo;

	public @FindBy(xpath = "//*[@id='editbtnPayByInvoice']") WebElement editbtnPayByInvoice;

	public @FindBy(xpath = ".//*[contains(@title, 'send email')]") WebElement clickSendPaymentHistoryEmail;

	public @FindBy(xpath = ".//*[contains(@title, 'cancel')]") WebElement clickCancelPaymentHistorySendEmail;

	@FindBy(xpath = "//a[contains(@class,'ng-binding')]")
	public WebElement DoubleClickID;

	@FindBy(id = "CustomerStatusId")
	WebElement CustomersatatusIdDropDown;

	// *[@id="angularScope"]/div[1]/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/a[8]
	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/a[8]")
	WebElement emailLoginUserLink;

	@FindBy(xpath = ".//*[@id='TabList']/div/table/tbody/tr/td[6]/input")
	WebElement CancelLockout;

	@FindBy(xpath = ".//*[@id='TabList']/div/table/tbody/tr/td[7]/div/i")
	WebElement clickAdmin_ResetPassword;

	@FindBy(xpath = ".//*[@id='formCompany']/div[2]/input")
	public WebElement updateButton;

	@FindBy(xpath = "//*[@id='ExtendedCredit']")
	WebElement ExtendedCredit;

	@FindBy(xpath = "//*[@id='formCredit']/div[3]/input")
	WebElement CreditSubmit;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[2]/li[3]/input"))
	WebElement Factoruncheck;

	public AdminLoginPage() throws IOException {
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

	public void AdminLogOut() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(logOut));
		logOut.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
	}

	public void Banking_editbtnPayByInvoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(editbtnPayByInvoice));
		editbtnPayByInvoice.click();
	}

	public void Link_PayMeNowTm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(PayMeNowTm));
		PayMeNowTm.click();
	}

	public void Link_delaydebit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(delaydebit));
		delaydebit.click();
	}

	public void Click_paymentterm45Submit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Clickpaymentterm45Submit));
		Clickpaymentterm45Submit.click();

	}
	
	public void submitLessThan14Days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(submitLessThan14Days));
		submitLessThan14Days.click();

	}
//	
//	public void paymentBetween14And45Days() throws InterruptedException {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(termDateMustBeBetween14And45DaysError));
//        Assert.assertEquals(termDateMustBeBetween14And45DaysError, "Error is not happening, user scheduled the payment!");
//	}
//	
	
	public void Clickverifysystemnote() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(verifysystemnote));
		verifysystemnote.click();
		wait.until(ExpectedConditions.elementToBeClickable(closenotesbutton));

	}
	
	public void systemGeneratedNoteEnabledLessThan14Days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(verifySystemNoteLessThan14DaysEnabled));
		verifySystemNoteLessThan14DaysEnabled.click();
		wait.until(ExpectedConditions.elementToBeClickable(closenotesbutton));

	}
	
	public void systemGeneratedNoteDisabledLessThan14Days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(verifySystemNoteLessThan14DaysDisabled));
		verifySystemNoteLessThan14DaysDisabled.click();
		wait.until(ExpectedConditions.elementToBeClickable(closenotesbutton));

	}
	

	public void Clickclosenotesbutton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(closenotesbutton));
		Thread.sleep(2000);
		closenotesbutton.click();

	}

	public void clickupdatebtnPayByInvoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_updatebtnPayByInvoice));
		click_updatebtnPayByInvoice.click();
	}

	public void clickenrollpaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(enrollpaymenow));
		enrollpaymenow.click();
	}

	public void selectPayByInvoieStatus() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(select_PayByInvoieStatus));
		Thread.sleep(1000);
		select_PayByInvoieStatus.click();
		s = new Select((select_PayByInvoieStatus));
		/* s.deselectByVisibleText("Active"); */
		s.selectByVisibleText("Enabled");
	}

	public void DisablePayByInvoieStatus() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(select_PayByInvoieStatus));
		Thread.sleep(1000);
		select_PayByInvoieStatus.click();
		s = new Select((select_PayByInvoieStatus));
		/* s.deselectByVisibleText("Active"); */
		s.selectByVisibleText("Disabled");
	}

	public void ClickOnCreditTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CreditTab));
		Thread.sleep(2000);
		CreditTab.click();
		wait.until(ExpectedConditions.elementToBeClickable(ExtendedCredit));
	}

	public void click_AdminBanking() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(AdminBanking));
		AdminBanking.click();
	}

	public void click_unenrolldelaydebit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(unenrolldelaydebit));
		unenrolldelaydebit.click();
	}

	public void ClickOnCreditSubmitButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CreditSubmit));
		CreditSubmit.click();
	}

	public void selectGreaterThan45daysId_Enabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectgreaterThan45daysId));
		selectgreaterThan45daysId.click();
		Select pay = new Select(selectgreaterThan45daysId);
		pay.selectByVisibleText("Enabled");
	}

	public void selectGreaterThan45daysId_Disabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectgreaterThan45daysId));
		selectgreaterThan45daysId.click();
		Select pay = new Select(selectgreaterThan45daysId);
		pay.selectByVisibleText("Disabled");
	}
	
	public void selectLessThan14Days_Enabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectLessThan14Days));
		selectgreaterThan45daysId.click();
		Select pay = new Select(selectLessThan14Days);
		pay.selectByVisibleText("Enabled");
	}

	public void selectLessThan14Days_Disabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectLessThan14Days));
		selectgreaterThan45daysId.click();
		Select pay = new Select(selectLessThan14Days);
		pay.selectByVisibleText("Disabled");
	}

	// public void selectGreaterThan45daysId(Boolean enabled) throws
	// InterruptedException {
	// wait.until(ExpectedConditions.elementToBeClickable(selectgreaterThan45daysId));
	// selectgreaterThan45daysId.click();
	// Select pay = new Select(selectgreaterThan45daysId);
	//
	// if (enabled) {
	// pay.selectByVisibleText("Enabled");
	// } else {
	// pay.selectByVisibleText("Disabled");
	// }
	// }

	public void EnterExtendedCredit(String CreditAmount) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(ExtendedCredit));
		ExtendedCredit.click();
		ExtendedCredit.clear();
		ExtendedCredit.sendKeys(CreditAmount);
	}

	public void ClickOnCustomersTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CustomerTab));
		Thread.sleep(1000);
		CustomerTab.click();
		Thread.sleep(1000);
	}

	public void ClickEditDelayDebit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(EditDelayDebit));
		Thread.sleep(1000);
		EditDelayDebit.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateDelayDebit));
	}

	public void ClickOnSearchBox(String keyword) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.click();
		search.clear();
		Thread.sleep(1000);
		search.sendKeys(keyword);
		Thread.sleep(2000);
	}

	public void ClickOnSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ClickonSearchButton));
		ClickonSearchButton.click();
		Thread.sleep(2000);
	}

	public void ClickSendEmailToVerify() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickSendPaymentHistoryEmail));
		clickSendPaymentHistoryEmail.click();
	}

	public void Clickupdatebtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(update_btn));
		update_btn.click();
	}

	public void ClickUpdatepaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Update_paymenow));
		Update_paymenow.click();
		Thread.sleep(2000);
	}

	public void ClickCloseButon() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CloseButon));
		CloseButon.click();
		Thread.sleep(2000);
	}

	public void ClickOKButon() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(okbtn));
		okbtn.click();
		Thread.sleep(2000);
	}

	public void ClickPaymentTerms() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(PaymentTerms));
		Thread.sleep(2000);
		PaymentTerms.click();
	}

	public void Click_Notes() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickNotes));
		clickNotes.click();
		wait.until(ExpectedConditions.elementToBeClickable(verifysystemnote));
	}

	public void Click_UpdateDelayDebit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(UpdateDelayDebit));
		UpdateDelayDebit.click();
		Thread.sleep(1000);
	}

	public void ClickCancelSendEmailToVerify() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickCancelPaymentHistorySendEmail));
		clickCancelPaymentHistorySendEmail.click();
	}

	public void DoubleClickID() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(DoubleClickID));
		DoubleClickID.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(CustomersatatusIdDropDown));
	}

	public void clickCustomerId() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CustomerId));
		CustomerId.click();
	}

	public void clickEmailLoginUserTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emailLoginUserLink));
		emailLoginUserLink.click();
	}

	public void click_AdminResetPassword() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickAdmin_ResetPassword));
		clickAdmin_ResetPassword.click();
	}

	public void clickAdmin_ResetpwdConfirm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_AdminResetpwdConfirm));
		click_AdminResetpwdConfirm.click();
	}

	public void clickEditPaymnttermgraterthan45days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Paymnttermgraterthan45days));
		Paymnttermgraterthan45days.click();
	}
	
	public void clickEditLessThan14Days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectEditLessThan14days));
		selectEditLessThan14days.click();
	}
	
	

	public void clickCancelLockout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CancelLockout));
		CancelLockout.click();
	}

	public void clickPaymentHistory() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(PaymentHistory));
		PaymentHistory.click();
	}

	public void clickcarrierPaymentHistory() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierPaymentHistory));
		carrierPaymentHistory.click();
	}

	public void clickuncheckpaymennow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(uncheckpaymennow));
		uncheckpaymennow.click();
	}

	public void clickpaymnt_Historydownload() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymnt_Historydownload));
		paymnt_Historydownload.click();
		Thread.sleep(2000);
	}

	public void clickpaymntcarrierHistorydownload() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymnt_carrierHistorydownload));
		paymnt_carrierHistorydownload.click();
		Thread.sleep(2000);
	}

	public void clicforwardfile() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(forwardfile));
		forwardfile.click();
	}

	public void clicklnkAdminPayMeNow() {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_AdminPayMeNow));
		lnk_AdminPayMeNow.click();
	}

	public void cliccarrierforwardfile() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierforwardfile));
		carrierforwardfile.click();
		Thread.sleep(6000);

	}

	public void EnterEmailTo(String keyword) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emailTo));
		emailTo.click();
		emailTo.clear();
		emailTo.sendKeys(keyword);
	}

	public void StatusIDDropDown() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CustomersatatusIdDropDown));
		CustomersatatusIdDropDown.click();
		Thread.sleep(1000);
		s = new Select(CustomersatatusIdDropDown);
		s.selectByVisibleText("Active");
		Thread.sleep(1000);

		log.info(s.getFirstSelectedOption().getText());
	}

	public void select_DelayDebitEnabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(delayedDebitStatusSelector));
		delayedDebitStatusSelector.click();
		s = new Select(delayedDebitStatusSelector);
		s.selectByVisibleText("Enabled");
		Thread.sleep(1000);
	}

	public void UpdateButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		updateButton.click();
		Thread.sleep(1000);
	}

	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return username;
	}

	public Boolean isDelayedDebitSelected() {

		Select select = new Select(delayedDebitStatusSelector);
		WebElement option = select.getFirstSelectedOption();

		if (option.getText().contains("Enabled"))
			return true;
		else
			return false;
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
		return loginBtn;
	}

	/**
	 * @return the logOut
	 */
	public WebElement getLogOut() {
		return logOut;
	}

	/**
	 * @return the customerTab
	 */
	public WebElement getCustomerTab() {
		return CustomerTab;
	}

	/**
	 * @return the customerId
	 */
	public WebElement getCustomerId() {
		return CustomerId;
	}

	/**
	 * @return the search
	 */
	public WebElement getSearch() {
		return search;
	}

	/**
	 * @return the click_AdminResetpwdConfirm
	 */
	public WebElement getClick_AdminResetpwdConfirm() {
		return click_AdminResetpwdConfirm;
	}

	/**
	 * @return the clickonSearchButton
	 */
	public WebElement getClickonSearchButton() {
		return ClickonSearchButton;
	}

	/**
	 * @return the doubleClickID
	 */
	public WebElement getDoubleClickID() {
		return DoubleClickID;
	}

	/**
	 * @return the customersatatusIdDropDown
	 */
	public WebElement getCustomersatatusIdDropDown() {
		return CustomersatatusIdDropDown;
	}

	/**
	 * @return the editloginuser
	 */
	public WebElement getEditloginuser() {
		return emailLoginUserLink;
	}

	/**
	 * @return the cancelLockout
	 */
	public WebElement getCancelLockout() {
		return CancelLockout;
	}

	/**
	 * @return the clickAdmin_ResetPassword
	 */
	public WebElement getClickAdmin_ResetPassword() {
		return clickAdmin_ResetPassword;
	}

	/**
	 * @return the updateButton
	 */
	public WebElement getUpdateButton() {
		return updateButton;
	}

	public WebElement getPaymentTerms() {
		// TODO Auto-generated method stub
		return PaymentTerms;
	}

	public WebElement getPaymnttermgraterthan45days() {
		// TODO Auto-generated method stub
		return Paymnttermgraterthan45days;
	}
	
	public WebElement editLessThan14Days() {
		// TODO Auto-generated method stub
		return selectEditLessThan14days;
	}
	
	
	
	

	public void Uncheck_Factor() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Factoruncheck));

		if (Factoruncheck.isSelected())
			Factoruncheck.click();

	}

	public void waitForLoadingToComplete() throws InterruptedException {
		if (loadingSpinner.isDisplayed()) {
			Thread.sleep(2000);
			waitForLoadingToComplete();
		}
	}
}