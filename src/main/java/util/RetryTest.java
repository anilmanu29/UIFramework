package util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

	private int count = 1;
	private static int maxTry = 3;

	public boolean retry(ITestResult iTestResult) {

		// Check if test has failed
		if (!iTestResult.isSuccess()) {
			// Check if maximum retry count is reached
			if (count < maxTry) {
				// Increase the maxTry count by 1
				count++;

				// Mark test as failed
				iTestResult.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				// If maxCount reached,test marked as failed
				iTestResult.setStatus(ITestResult.FAILURE);
			}
		} else {
			// If test passes, TestNG marks it as passed
			iTestResult.setStatus(ITestResult.SUCCESS);
		}

		return false;
	}
}