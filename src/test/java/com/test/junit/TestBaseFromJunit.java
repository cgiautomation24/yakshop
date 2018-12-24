package com.test.junit;

import static io.restassured.RestAssured.baseURI;
import static utility.Constants.BASE_URL;


public class TestBaseFromJunit {

	public TestBaseFromJunit() {
		baseURI = BASE_URL;
	}
	
	/*@BeforeClass
	public static void beforeClass() {
		System.out.println("Begin Junit Class :: ");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("Finish Junit Class ::");
	}*/
}
