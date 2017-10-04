package com.sand.box;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CommonUtil extends SetUp {

	private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);


	/**
	 * Click by href identifier
	 * 
	 * @param hrefIdentifier
	 *            href identifier
	 */
	public void clickHref(String hrefIdentifier) {

		waitForHref(hrefIdentifier);

		driver.findElement(By.cssSelector("a[href*='" + hrefIdentifier + "']"))
				.click();
	}

	/**
	 * Clicks link by text of link
	 * 
	 * @param Link_Text
	 *            The text of the link to be pressed - case sensitive
	 */
	public void clickLink(String Link_Text) {

		waitForLink(Link_Text);

		driver.findElement(By.linkText(Link_Text)).click();
	}

	/**
	 * Clicks link by partial text of link
	 * 
	 * @param Link_Text
	 *            The partial text of the link to be pressed - case sensitive
	 */
	public void clickPartialLink(String Link_Text) {

		waitForPartialLink(Link_Text);

		driver.findElement(By.partialLinkText(Link_Text)).click();
	}

	/**
	 * Clicks by Xpath
	 * 
	 * @param xpath_x
	 *            Xpath of link or button
	 */
	public void clickXpath(String xpath_x) {

		waitForXpath(xpath_x);

		driver.findElement(By.xpath(xpath_x)).click();
	}

	/**
	 * Clicks by cssSelector
	 * 
	 * @param css
	 *            cssSelector of link
	 */
	public void clickCss(String css) {

		waitForCssPath(css);

		driver.findElement(By.cssSelector(css)).click();
	}

	/**
	 * Click button by button name
	 * 
	 * @param name_x
	 *            button name
	 */
	public void clickName(String description, String name_x) {

		waitForButtonName(name_x);

		driver.findElement(By.name(name_x)).click();
	}

	/**
	 * Click button by button id
	 * 
	 * @param id_x
	 *            id
	 */
	public void clickId(String id_x) {

		waitForClickableId(id_x);

		driver.findElement(By.id(id_x)).click();
	}

	/**
	 * Click OK on windows alert
	 * 
	 */
	public void acceptPopup(WebDriver driver) {

		try {

			log.info("Going to popup and clicking OK");

			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (Exception e) {
			log.error("Unable to locate popup and or OK button");
			log.error("Fail ", e);
			Assert.fail();
		}
	}

	/**
	 * Click Cancel on windows alert
	 * 
	 */
	public void cancelPopup(WebDriver driver) {

		try {

			log.info("Going to popup and clicking Cancel");

			Alert alert = driver.switchTo().alert();
			alert.dismiss();

		} catch (Exception e) {
			log.error("Unable to locate popup and or Cancel button");
			log.error("Fail ", e);
			Assert.fail();
		}
	}

	/**
	 * Used to do a screen capture
	 * 
	 * @return
	 */
	public String captureScreen() {

		String path;
		try {
			// WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			String datestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_")
					.format(new Date());
			path = "C:\\workspace\\screenshot\\errors\\" + datestamp
					+ source.getName();
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
		return path;
	}

	/**
	 * Use to validate content on a page
	 * 
	 * @param Page_Content
	 *            Content to be verified on the page
	 * @param Print_on_Pass
	 *            Pass message to be logged
	 * @param Print_on_Fail
	 *            Fail message to be logged
	 */
	public void validatePage(String Page_Content, String Print_on_Pass,
			String Print_on_Fail) {

		if (driver.getPageSource().contains(Page_Content)) {
			log.info(Print_on_Pass);
		} else {
			captureScreen();
			log.error(Print_on_Fail);
		}
	}

	/**
	 * Wait for Xpath element that can be clicked - timeout 30seconds
	 * 
	 * @param xpath
	 *            Xpath locator
	 */
	public void waitForXpath(String xpath) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	/**
	 * Wait for Link to be clicked - timeout 30seconds
	 * 
	 * @param link_text
	 *            Link Text - case sensitive
	 */
	public void waitForLink(String link_text) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.elementToBeClickable(By
				.linkText(link_text)));
	}

	/**
	 * Wait for Partial Link to be clicked - timeout 30seconds
	 * 
	 * @param partial_link_text
	 *            Partial link text
	 */
	public void waitForPartialLink(String partial_link_text) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.partialLinkText(partial_link_text)));
	}

	/**
	 * Wait for button by button name - timeout 30seconds
	 * 
	 * @param name_x
	 *            Button name
	 */
	public void waitForButtonName(String name_x) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.name(name_x)));
	}

	/**
	 * Wait for button by button ID - timeout 30seconds
	 * 
	 * @param id_x
	 *            Button ID
	 */
	public void waitForClickableId(String id_x) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id_x)));
	}

	/**
	 * Wait for button by button CssSelector - timeout 30seconds
	 * 
	 * @param css
	 *            Button CssSelector
	 */
	public void waitForCssPath(String css) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	}

	/**
	 * Wait for href identifier to be present
	 * 
	 * @param hrefIdentifier
	 *            href identifier
	 */
	public void waitForHref(String hrefIdentifier) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("a[href*='" + hrefIdentifier + "']")));
	}

	/**
	 * Wait for text to be present - timeout 30seconds
	 * 
	 * @param xpath_x
	 *            Text Xpath
	 * @param text
	 *            Text to wait for - case sensitive
	 */
	public void waitForText(String xpath_x, String text) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath(xpath_x), text));
	}

	/**
	 * Wait for Frame by ID - timeout 30seconds
	 * 
	 * @param id_x
	 *            Frame ID
	 */
	public void waitForFrameIdAndSwitchTo(String id_x) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By
				.id(id_x)));
	}

	/**
	 * Wait for Frame by name - timeout 30seconds
	 * 
	 * @param name_x
	 *            Frame name
	 */
	public void waitForFrameNameAndSwitchTo(String name_x) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By
				.name(name_x)));
	}

	/**
	 * Thread sleep
	 * 
	 * @param millis
	 *            Milliseconds to pause
	 */
	public void threadSleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Robot Move to using X, Y coordinate
	 * 
	 * @param x_coordinate
	 *            x coordinate
	 * @param y_coordinate
	 *            y coordinate
	 */
	public void useRobot(int x_coordinate, int y_coordinate) {

		try {

			Robot rob = new Robot();

			rob.mouseMove(x_coordinate, y_coordinate + 100);

		} catch (Exception e) {
			log.error("Robot failed. Please check coordinates.");
			log.error("Fail ", e);
			Assert.fail();
			driver.quit();
		}
	}

	/**
	 * Generic Radio button method
	 * 
	 * @param answer
	 *            Radio Button - Yes or No only
	 * @param description
	 *            "Choosing " + answer + " radio button on the " + description +
	 *            "." / "Unable to locate " + answer + " radio button on the " +
	 *            description + "."
	 * @param id_Yes
	 *            ID of yes answer
	 * @param id_No
	 *            ID of no answer
	 */
	public void radioButtonId(String answer, String description, String id_Yes,
			String id_No) {
		try {

			log.info("Choosing " + answer + " radio button on the "
					+ description + ".");

			if (answer == "Yes") {
				waitForClickableId(id_Yes);
				driver.findElement(By.id(id_Yes)).click();
			} else if (answer == "No") {
				waitForClickableId(id_No);
				driver.findElement(By.id(id_No)).click();
			}

		} catch (NoSuchElementException | ElementNotVisibleException e) {
			log.error("Fail: Unable to locate " + answer
					+ " radio button on the " + description + ". ", e);
			Assert.fail();
		}
	}

	/**
	 * Enter txt using CssSelector
	 * 
	 * @param css
	 *            CssSelector
	 * @param enteredText
	 *            text to be entered (sendkeys)
	 */
	public void enterTextCss(String css, String enteredText) {

		driver.findElement(By.cssSelector(css)).sendKeys(enteredText);
	}

	/**
	 * Enter txt using Id
	 * 
	 * @param id_x
	 *            Id
	 * @param enteredText
	 *            text to be entered (sendkeys)
	 */
	public void enterTextId(String id_x, String enteredText) {
		
		waitForClickableId(id_x);

		driver.findElement(By.id(id_x)).sendKeys(enteredText);
	}

	/**
	 * Enter txt using Name
	 * 
	 * @param name_x
	 *            Name
	 * @param enteredText
	 *            text to be entered (sendkeys)
	 */
	public void enterTextName(String name_x, String enteredText) {

		driver.findElement(By.name(name_x)).sendKeys(enteredText);
	}

	/**
	 * Kill Selenium session
	 */
	public void killSelenium() {

		driver.close();
		driver.quit();
	}
}