package com.sand.box;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass extends SetUp {
	
	
	private static final Logger log = LoggerFactory
			.getLogger(TestClass.class);

	
	private static final String Test_Name = "Sandbox Test";
	
	
	@BeforeTest
	public void setup() {
		log.info("Starting Test Case: " + Test_Name);
	}

	@AfterTest
	public void teardown() {
		log.info("Finished Test Case: " + Test_Name);
	}
	
	@Test
	public void runThisTest() {
		//your test
	}
}
