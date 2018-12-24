package com.test.junit;

import static io.restassured.RestAssured.given;
import static utility.Constants.TestServer;
import static utility.Constants.TestServerUpwithInvalidPath;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



/*
 *  Server status Up or Not
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServerStatusJunit extends TestBaseFromJunit{

	@Test
	public void test_checkServerUP() {
		int statuscode = given().basePath(TestServer).get().then().assertThat().contentType("text/plain").extract()
				.statusCode();
		Assert.assertEquals("Service is Not Up and Running",200, statuscode);
	}

	@Test
	public void test_checkServerUP_withInvalidPath() {
		int statuscode = given().basePath(TestServerUpwithInvalidPath).get().then().assertThat()
				.contentType("text/html").extract().statusCode();
		Assert.assertEquals("Error 404 Not Found",404, statuscode);
	}

}
