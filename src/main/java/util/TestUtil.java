package util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.opencsv.CSVReader;

import base.TestBase;

public class TestUtil extends TestBase {

	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 20;

	public static String downloadsPath = System.getProperty(userHome) + "\\Downloads";

	public static String basePath = "C:/AUTOMATION/SELENIUM/_Project/testing/RevolutFramework/";
	public static String initialVideoFilePath = basePath + "testVideo.mkv";
	public static String finalVideoFilePath = basePath + "ffmpeg_video_capture/videoFiles/";
	public static String videoCaptureBinPath = basePath + "ffmpeg_video_capture/bin/record.bat";
	public static Boolean videoStarted = false;

	Workbook book;
	Sheet sheet;
	static Robot robot;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TestBase.RevolutTestDataFilePath);
		} catch (FileNotFoundException e) {
			log.info(e);
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			log.info(e);
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	public void takeScreenshotAtEndOfTest() throws IOException {

		Calendar now = Calendar.getInstance();

		int currentYear = now.get(Calendar.YEAR);
		int currentMonth = now.get(Calendar.MONTH) + 1;

		String strYear = Integer.toString(currentYear);
		String strMonth = "";

		if (currentMonth < 10)
			strMonth = "0" + Integer.toString(currentMonth);
		else
			strMonth = Integer.toString(currentMonth);

		// (1) get today's date
		Date today = Calendar.getInstance().getTime();

		// (2) create a date "formatter" (the date format we want)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_zzz");

		// (3) create a new String using the date format we want
		String fileName = className + "_" + formatter.format(today);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		FileUtils.copyFile(scrFile, new File(
				currentDir + "/output/screenshots/" + strYear + "/" + strMonth + File.separator + fileName + ".png"));

	}

	// this function takes a single string and removes the .0 that Excel likes to
	// add regardless of formatting
	public static String removeDecimalZeroFormat(String strData) {
		if (strData.contains(".0"))
			strData = strData.replace(".0", "");

		return strData;
	}

	public static void DateFormatTestExample(String[] args) {
		Calendar currentDateTime = Calendar.getInstance();
		log.info("---------------------------------------------------");
		log.info("-- Get Year/Month/Day/Hour/Minute using Calendar --");
		log.info("---------------------------------------------------");
		log.info("Year - Calendar [" + currentDateTime.get(Calendar.YEAR) + "]");
		log.info("Month - Calendar [" + currentDateTime.get(Calendar.MONTH) + "]");
		log.info("Day - Calendar [" + currentDateTime.get(Calendar.DAY_OF_MONTH) + "]");
		log.info("Hour - Calendar [" + currentDateTime.get(Calendar.HOUR_OF_DAY) + "]");
		log.info("Minute - Calendar [" + currentDateTime.get(Calendar.MINUTE) + "]");
		log.info("-------------------------------------------------------------------------");
		log.info("-- Get Year/Month/Day/Hour/Minute and Time Zone using SimpleDateFormat --");
		log.info("-------------------------------------------------------------------------");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
		SimpleDateFormat timeZoneFormat = new SimpleDateFormat("zzz");

		Date currentDate = new Date(currentDateTime.getTimeInMillis());
		log.info("Year - SimpleDateFormat [" + yearFormat.format(currentDate) + "]");
		log.info("Month - SimpleDateFormat [" + monthFormat.format(currentDate) + "]");
		log.info("Day - SimpleDateFormat [" + dayFormat.format(currentDate) + "]");
		log.info("Hour - SimpleDateFormat [" + hourFormat.format(currentDate) + "]");
		log.info("Minute - SimpleDateFormat [" + minuteFormat.format(currentDate) + "]");
		log.info("Time Zone - SimpleDateFormat [" + timeZoneFormat.format(currentDate) + "]");
	}

	public static Boolean verifyFileDownload(String searchedFileName) {

		String path = System.getProperty(userHome) + "\\Downloads";

		File folder = new File(path);
		File[] files = folder.listFiles();

		log.info("Searched file name: " + searchedFileName);

		for (File currentFile : files) {
			log.info("Current file name: " + currentFile);
			if (currentFile.getName().contains(searchedFileName)) {
				return true;
			}
		}

		return false;
	}

	public static Integer getFileCount() {
		int fileCount = 0;

		File folder = new File(downloadsPath);
		File[] files = folder.listFiles();
		fileCount = files.length;

		return fileCount;
	}

	public static List<String[]> getCsvContents(String filePath) throws IOException {

		CSVReader csvReader = null;
		List<String[]> list = null;
		String fullPath = downloadsPath + "\\" + filePath;

		try {
			csvReader = new CSVReader(new FileReader(fullPath));
			list = csvReader.readAll();

			log.info("\n\n==============CSV CONTENTS====================");

			for (int i = 0; i < list.size(); i++) {
				log.info(Arrays.toString(list.get(i)));
			}

			log.info("\n==============================================\n");

		} catch (FileNotFoundException e) {
			log.info(e);
		} finally {
			if (csvReader != null)
				csvReader.close();
		}

		return list;
	}

	/*
	 * EXAMPLE CODE FOR WRITING TO CSV List<String[]> myList = new ArrayList<>();
	 * myList.add(new String[] { "Name", "Class", "Marks" }); myList.add(new
	 * String[] { "Aman", "10", "620" }); myList.add(new String[] { "Suraj", "10",
	 * "630" });
	 * 
	 * TestUtil.writeDataForCustomSeperatorCSV( TestUtil.basePath +
	 * "\\output\\csv files\\testFile " + TestUtil.getCurrentDateTime() + ".csv",
	 * myList);
	 */
	public static void writeDataForCustomSeperatorCSV(String filePath, String[][] dataList, String seperator)
			throws IOException {
		StringBuilder builder = new StringBuilder();

		int rowCount = dataList.length;
		int colCount = dataList[0].length;

		for (int i = 0; i < rowCount; i++)// for each row
		{
			for (int j = 0; j < colCount; j++)// for each column
			{
				if (j != (colCount - 1))
					builder.append(dataList[i][j] + seperator);// append to the output string
				else
					builder.append(dataList[i][j]);// append to the output string
			}

			builder.append("\n");// append new line at the end of the row
		}

		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(filePath));
		writer.write(builder.toString());// save the string representation of the board
		writer.close();
	}

	public static long getDifferenceBetweenDates(String startDate, String endDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date firstDate = sdf.parse(startDate);
		Date secondDate = sdf.parse(endDate);

		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());

		return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public static int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	public static void zoomIN() throws InterruptedException, AWTException {
		robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);

	}

	public static String getCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	public static void beginVideoCapture() {
		try {

			String[] startCmd = { "cmd", "/c", "start", videoCaptureBinPath };
			Runtime.getRuntime().exec(startCmd);
			videoStarted = true;
		} catch (IOException e) {
			log.info(e);
		}
	}

	public static void endVideoCapture() throws IOException {
		killProcess("ffmpeg.exe");
		killProcess("cmd.exe");
		videoStarted = false;
	}

	public static void updateVideoFileName() throws InterruptedException {

		if (TestUtil.className.contains("testcases."))
			TestUtil.className = TestUtil.className.replaceAll("testcases.", "");

		String newFilePath = finalVideoFilePath + TestUtil.className + "_" + TestUtil.getCurrentDateTime() + "_.mkv";

		File file = new File(initialVideoFilePath);
		File newFile = new File(newFilePath);

		Thread.sleep(5000);

		if (file.renameTo(newFile)) {
			log.info("File rename success");
		} else {
			log.info("File rename failed");
		}
	}

	public static void killProcess(String process) throws IOException {
		Runtime.getRuntime().exec("TASKKILL /F /IM " + process);
	}

	public static String[] getTimestamp() {

		Date currentTime;
		String formattedDate = "";
		Long longTime;
		DateFormat formatter;
		String currentHour = "";
		String currentMinutes = "";
		String timeArray[] = new String[2];

		currentTime = new Date();
		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("MST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];

		log.info("\n\n\n===============================");
		log.info("Current date: " + longTime);
		log.info("Formatted date: " + formattedDate);
		log.info("Current Hour: " + currentHour);
		log.info("Current Minutes: " + currentMinutes);
		log.info("===============================");

		return timeArray;
	}

	public static String getBodyResponse(String URL) throws IOException {
		URL url = new URL(URL);
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		System.out.println(body);

		return body;
	}
}
