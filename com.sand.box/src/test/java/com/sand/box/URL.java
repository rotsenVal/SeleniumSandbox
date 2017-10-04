package com.sand.box;

import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class URL extends SetUp {
	
	private static final Logger log = LoggerFactory
			.getLogger(URL.class);

public void goToURL(String url) {
		
		try {

			log.info("Going to http://" + url);
			
			driver.get("http://" + url);

		} catch (NoSuchElementException e) {

			log.error("Fail: Unable to naviagete to http://" + url , e);
			Assert.fail();
		}
	}
	
}