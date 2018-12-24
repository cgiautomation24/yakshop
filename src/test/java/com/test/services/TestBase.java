package com.test.services;

import static io.restassured.RestAssured.baseURI;
import static utility.Constants.BASE_URL;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	Logger log = Logger.getLogger(TestBase.class);
	JSONObject jsonobject;

	public TestBase() {
		baseURI = BASE_URL;
	}

	@BeforeSuite
	public void begin() {
		log.info("Start restAPI validation");
	}

	@AfterSuite
	public void afterClass() {
		log.info("Finish restAPI validation");
	}

}
