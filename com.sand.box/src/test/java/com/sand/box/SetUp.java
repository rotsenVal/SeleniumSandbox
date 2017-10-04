package com.sand.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SetUp {

	public static WebDriver driver;
	DesiredCapabilities caps = new DesiredCapabilities();
	public static final String USERNAME = "TEST";
	public static final String AUTOMATE_KEY = "TEST";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub.browserstack.com/wd/hub";

	@BeforeClass
	public void setUp() {
		InputStream input = null;
		Properties prop = new Properties();

		try {
			input = new FileInputStream(
					(new File(
							"C:\\workspace\\com-automation-portal\\src\\test\\resources\\test.properties")));
			try {
				prop.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String browser = (prop.getProperty("browser"));

			if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\workspace\\Drivers\\Chrome\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equals("ie9")) {
				System.setProperty("webdriver.ie.driver",
						"C:\\workspace\\Drivers\\IE64\\IEDriverServer.exe");
				final DesiredCapabilities caps = DesiredCapabilities
						.internetExplorer();
				caps.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				caps.setCapability("ignoreProtectedModeSettings", true);
				driver = new InternetExplorerDriver(caps);

			} else if (browser.equals("ie8")) {
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "8.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "7");
				caps.setCapability("resolution", "1024x768");
				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.local", "true");
				caps.setCapability("browserstack.localIdentifier", "Nestor");
				try {
					driver = new RemoteWebDriver(new URL(URL), caps);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else if (browser.equals("ie11")) {
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "11.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "8.1");
				caps.setCapability("resolution", "1024x768");
				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.local", "true");
				caps.setCapability("browserstack.localIdentifier", "Nestor");
				try {
					driver = new RemoteWebDriver(new URL(URL), caps);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
