package testngcustomreportlistener;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

public class TestNGCustomReportListener implements IReporter {
	private ExtentReports extent;
	Logger log = Logger.getLogger(Log.class.getName());

	// private EmailableReporter emailable;

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) {

		// emailable = (EmailableReporter) new ExtentReports(outputDirectory +
		// "/output/Reports/" + System.currentTimeMillis() + File.separator
		// + "emailable-report.html", true);

		extent = new ExtentReports(outputDirectory + File.separator + "emailable-report.html", true);

		// Second parameter of this method ISuite will contain all the suite executed.
		for (ISuite iSuite : arg1) {

			// Get a map of result of a single suite at a time
			Map<String, ISuiteResult> results = iSuite.getResults();

			// Get the key of the result map
			Set<String> keys = results.keySet();

			// Go to each map value one by one
			for (String key : keys) {

				// The Context object of current result
				ITestContext context = results.get(key).getTestContext();

				// Print Suite detail in Console
				log.info("Suite Name->" + context.getName() + "\nReport output directory->"
						+ context.getOutputDirectory() + "\nSuite Name->" + context.getSuite().getName()
						+ "\nStart Date-Time for execution->" + context.getStartDate()
						+ "\nEnd Date-Time for execution->" + context.getEndDate());

				// Get Map for only failed test cases
				IResultMap resultMap = context.getFailedTests();

				// Get method detail of failed test cases
				Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();

				// Loop one by one in all failed methods
				log.info("\n\n--------FAILED TEST CASE---------");

				for (ITestNGMethod iTestNGMethod : failedMethods) {

					// Print failed test cases detail
					log.info("TESTCASE NAME->" + iTestNGMethod.getMethodName() + "\nDescription->"
							+ iTestNGMethod.getDescription() + "\nPriority->" + iTestNGMethod.getPriority()
							+ "\n:Date->" + new Date(iTestNGMethod.getDate()));
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private String timeConversion(long seconds) {

		final int MINUTES_IN_AN_HOUR = 60;
		final int SECONDS_IN_A_MINUTE = 60;

		int minutes = (int) (seconds / SECONDS_IN_A_MINUTE);
		seconds -= minutes * SECONDS_IN_A_MINUTE;

		int hours = minutes / MINUTES_IN_AN_HOUR;
		minutes -= hours * MINUTES_IN_AN_HOUR;

		return prefixZeroToDigit(hours) + ":" + prefixZeroToDigit(minutes) + ":" + prefixZeroToDigit((int) seconds);
	}

	private String prefixZeroToDigit(int num) {
		int number = num;
		if (number <= 9) {
			return "0" + number;
		} else
			return "" + number;

	}

	@SuppressWarnings("unused")
	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}