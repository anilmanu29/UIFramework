package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import util.TestUtil;
import util.WebEventListener;
import utility.ReadExcel;

public class TestBase {

	public static WebDriver driver;
	protected static Properties prop = new Properties();
	protected static EventFiringWebDriver eDriver;
	protected static WebEventListener eventListener;
	protected static String userDirectory = "user.dir";
	protected static String userHome = "user.home";
	public static Logger log;
	public static String className = "";
	public static String jiraIssueID = "";
	private String jiraUserName = "";
	private String jiraPassword = "";

	public static Boolean testCompleted = false;

	public static WebDriverWait wait = null;
	public static String loadPayTestDataFilePath = System.getProperty(userDirectory)
			+ "/src/main/java/testdata/LoadPay/LoadPayTestData.xlsx";
	public static String salesforceTestDataFilePath = System.getProperty(userDirectory)
			+ "/src/main/java/testdata/SalesForce/SalesForceTestData.xlsx";
	public static String freightMatchingTestDataFilePath = System.getProperty(userDirectory)
			+ "/src/main/java/testdata/FreightMatching/FreightMatchingTestData.xlsx";

	public TestBase() {
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty(userDirectory) + "/src/main/java/config/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			log.info(e);
		}
	}

	public static void initialization() {
		log = Logger.getLogger(Logger.class.getName());

		if (prop.getProperty("useVideoCapture").contains("true")) {
			TestUtil.beginVideoCapture();
		}

		String browserName = prop.getProperty("browser");

		log.info("Selecting browser type from configuration properties");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty(userDirectory) + "/Drivers/chromedriver.exe");
			// driver = new ChromeDriver();

			Map<String, Object> prefs = new HashMap<String, Object>();
			// To Turns off multiple download warning
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			// Turns off download prompt
			prefs.put("download.prompt_for_download", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);

		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty(userDirectory) + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty(userDirectory) + "/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		// DETERMINE APPLICATION UNDER TEST FROM CLASS NAME
		if (className.contains("loadpay"))
			driver.get(prop.getProperty("RevoltURL"));
		else if (className.contains("freightmatching"))
			driver.get(prop.getProperty("freightmatchingurl"));
		else if (className.contains("itsdispatch"))
			driver.get(prop.getProperty("ITSDispatchURL"));
		else if (className.contains("mobile"))
			driver.get(prop.getProperty("mobileURL"));
		else if (className.contains("v5prototype"))
			driver.get(prop.getProperty("v5_prototypeURL"));
		else if (className.contains("salesforce"))
			driver.get(prop.getProperty("SalesforceURL"));

		// USE FULL LOADPAY REGRESSION INPUT DATA - HAS MORE NEW PAYMENT LINES
//		if (prop.getProperty("useFullRegressionData").contains("true")) {
//			loadPayTestDataFilePath = System.getProperty(userDirectory)
//					+ "/src/main/java/testdata/LoadPay/LoadPayTestData_FullRegression.xlsx";
//		}

		// Create object of EventListerHandler to register it with EventFiringWebDriver
		eDriver = new EventFiringWebDriver(driver);
		eventListener = new util.WebEventListener();
		eDriver.register(eventListener);
		driver = eDriver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	public static Properties getProperties() {
		return prop;
	}

	@DataProvider
	public Object[][] getRevoltadminloginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "RevoltadminloginData");
	}

	@DataProvider
	public Object[][] getStagingBrokerLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "HandshakeBrokerLogindata");
	}

	@DataProvider
	public Object[][] getBrokerRegisterData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerRegister");
	}

	@DataProvider
	public Object[][] getCarrierLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierLoginData");
	}

	@DataProvider
	public Object[][] getStagingCarrierLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "HandshakeCarrierLogindata");
	}

	@DataProvider
	public Object[][] getPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerNewPaymentData");
	}

	@DataProvider
	public Object[][] getCarrierRegisterData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierRegisterData");
	}

	@DataProvider
	public Object[][] getoutlookLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "outlookLoginData");
	}

	@DataProvider
	public Object[][] getAdminLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminLogin");
	}

	@DataProvider
	public Object[][] getPaymentDataforUnmatchcarrier() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerPaymentDataforUnmatchedCr");
	}

	@DataProvider
	public Object[][] getBrokerData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerRegisterCanada");
	}

	@DataProvider
	public Object[][] getCarrierData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierRegisterCanada");
	}

	@DataProvider
	public Object[][] getCarrierFuelcardaccountNumbersData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierFuelcardaccountNumbers");
	}

	@DataProvider
	public Object[][] getCarrierBankingData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierBankingData");
	}

	@DataProvider
	public Object[][] getBrokerBankingData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerBankingData");
	}

	@DataProvider
	public Object[][] getCcarrierMatchedPayByCheckPayMNWData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CcarrierMatchedPayByCheckPayMNW");
	}

	@DataProvider
	public Object[][] getCarrierPaidTabData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierPaidTabData");
	}

	@DataProvider
	public Object[][] getCarrierlockedaccountAdminUnlockData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierlockedaccountAdminUnlock");
	}

	@DataProvider
	public Object[][] getCarrierParentChildData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "carrierparentchilddata");
	}

	@DataProvider
	public Object[][] getCarrierParentChildPasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "carrierresetpassworddata");
	}

	@DataProvider
	public Object[][] getBulkUploadPaymentsmatchedData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BulkUploadPaymentsmatched");
	}

	@DataProvider
	public Object[][] getBulkUploadPaymentsUnmatchedData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BulkUploadPaymentsUnmatched");
	}

	@DataProvider
	public Object[][] getCarrierSchedulePaymentTabData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierSchedulePaymentTabData");
	}

	@DataProvider
	public Object[][] getAdminSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminSearchData");
	}

	@DataProvider
	public Object[][] getBrokerChangePasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerChangePasswordData");
	}

	@DataProvider
	public Object[][] getBrokerForgotPassword() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerForgotPassword");
	}

	@DataProvider
	public Object[][] getCarrierPaymentHistoryData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierPaymentHistorydata");
	}

	@DataProvider
	public Object[][] getBrokerPaymentHistoryData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerPaymentHistorydata");
	}

	@DataProvider
	public Object[][] getShipperCompleteAccModuleData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "ShipperCompleteAccModuleData");
	}

	@DataProvider
	public Object[][] getBrokerProcessedTabSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerProcessedTabSearchData");
	}

	@DataProvider
	public Object[][] getBrokerDiscountsTabSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerDiscountsTabSearchData");
	}

	@DataProvider
	public Object[][] getBrokerSchedPaymentTabSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerSchedPaymentTabSearchData");
	}

	@DataProvider
	public Object[][] getUpdatedPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerUpdatePaymentData");
	}

	@DataProvider
	public Object[][] getAdminLoginshipperaccountmoduleData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminLoginshipperaccountmodule");
	}

	@DataProvider
	public Object[][] getCarrierChangePasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierChangePasswordData");
	}

	@DataProvider
	public Object[][] getCarrierForgotPasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierForgotPasswordData");
	}

	@DataProvider
	public Object[][] getCarrierLoginAccountModuleData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierLoginAccountModuleData");
	}

	@DataProvider
	public Object[][] getAdminforcepasswordData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminforcepasswordData");
	}

	@DataProvider
	public Object[][] getBrokerBankAccountData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BankAccountDetailsForCopyPaste");
	}

	@DataProvider
	public Object[][] getCarrierBankAccountData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierBankAccountDetails");
	}

	@DataProvider
	public Object[][] getRTFLogindata() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "RTFLogin");
	}

	@DataProvider
	public Object[][] getExtendedCreditAmount() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CreditAmount");
	}

	@DataProvider
	public Object[][] getCreditAmount() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "ExtendedCreditAmount");
	}

	@DataProvider
	public Object[][] getLoginHandshakewithRTF_CarrierDa() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "LoginHandshakewithRTF_CarrierDa");
	}

	@DataProvider
	public Object[][] getBrokerCanNotRegisterDuplicateEmailData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerDuplicateEmail");
	}

	@DataProvider
	public Object[][] getCarrierCanNotRegisterDuplicateEmailData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierDuplicateEmail");
	}

	@DataProvider
	public Object[][] getITSDispatchLogindata() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "ITSDispatchLoginData");
	}

	@DataProvider
	public Object[][] getUpdatedBrokerPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "BrokerUpdatedPaymentData");
	}

	@DataProvider
	public Object[][] getUpdatedCarrierPaymentData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "CarrierUpdatePaymentData");
	}

	@DataProvider
	public Object[][] getAdminPaymentsGreaterthan45Days() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminPaymentsGreaterthan45Days");
	}

	@DataProvider
	public Object[][] getpayementmorethan45daysData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "payementmorethan45daysData");
	}

	@DataProvider
	public Object[][] getAdminCustomersSideMenSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "AdminCustomersSideMenSearchData");
	}

	@DataProvider
	public Object[][] getSecurityQuestionsAndAnswers() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(loadPayTestDataFilePath, "SecurityQandA");
	}

	@DataProvider
	public Object[][] getsalesforceLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "SalesforceLogin");
	}

	@DataProvider
	public Object[][] getSalesForceAccountCreationData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "SalesForceAccountCreation");
	}

	@DataProvider
	public Object[][] getContactCreationData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "ContactCreation");
	}

	@DataProvider
	public Object[][] getNewQuoteData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "NewQuote");
	}

	@DataProvider
	public Object[][] getsalesforceoutlookLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "outlookLoginData");
	}

	@DataProvider
	public Object[][] getLeadCreationData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "LeadCreationData");
	}

	@DataProvider
	public Object[][] getMultiAccountCreationData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(salesforceTestDataFilePath, "MultiAccountCreationData");
	}

	@DataProvider
	public Object[][] getProUserLoginData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(freightMatchingTestDataFilePath, "ProLoginData");
	}

	@DataProvider
	public Object[][] getLoadSearchData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(freightMatchingTestDataFilePath, "LoadSearchData");
	}

	@DataProvider
	public Object[][] getLoadPostData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();
		return read.getCellData(freightMatchingTestDataFilePath, "LoadPostData");
	}


	@AfterClass
	public void quit() {
		log.info("END TEST\n\n");

		if (TestUtil.videoStarted && prop.getProperty("useVideoCapture").contains("true")) {
			try {
				TestUtil.endVideoCapture();
			} catch (IOException e) {
				log.info(e);
			}

			try {
				TestUtil.updateVideoFileName();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		driver.quit();
	}
}
