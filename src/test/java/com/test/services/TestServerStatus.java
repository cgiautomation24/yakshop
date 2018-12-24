package com.test.services;

import static io.restassured.RestAssured.given;
import static utility.Constants.TestServer;
import static utility.Constants.TestServerUpwithInvalidPath;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
 *  Server status Up or Not
 */
public class TestServerStatus extends TestBase {

	@Test(description = "Check Server Status", priority = 0)
	public void test_checkServerUP() {
		int statuscode = given().basePath(TestServer).get().then().assertThat().contentType("text/plain").extract()
				.statusCode();
		Assert.assertEquals(200, statuscode, "Service is Not Up and Running");
	}

	@Test(description = "Check Server Status", priority = 1)
	public void test_checkServerUP_withInvalidPath() {
		int statuscode = given().basePath(TestServerUpwithInvalidPath).get().then().assertThat()
				.contentType("text/html").extract().statusCode();
		Assert.assertEquals(404, statuscode, "Error 404 Not Found");
	}

}
