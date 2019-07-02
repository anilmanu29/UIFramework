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



	public static Boolean testCompleted = false;

	public static WebDriverWait wait = null;
	public static String RevolutTestDataFilePath = System.getProperty(userDirectory)
			+ "/src/main/java/testdata/Revolut/RevolutTestData.xlsx";

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
		if (className.contains("revolut"))
			driver.get(prop.getProperty("RevolutURL"));
		else if (className.contains("communityrevolut"))
			driver.get(prop.getProperty("RevolutCommunityUrl"));


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
		return read.getCellData(RevolutTestDataFilePath, "RevoltadminloginData");
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
