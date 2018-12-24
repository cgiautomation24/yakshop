package com.test.services;

import static io.restassured.RestAssured.given;
import static utility.Constants.ReloadHerd;
import static utility.Constants.TestStockDay1;
import static utility.Constants.TestStockDay2;
import static utility.Constants.TestStockwithInvalidDay;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

/*
 *  Assert Stock availability by day wise and validating with request with positive and negative scenarios
 */
public class TestStock extends TestBase {

	@Test(description = "Stock availability for day 1", priority = 0)
	public void test_checkStock_day_1() {
		String response = given().basePath(TestStockDay1).get().asString();
		//Assert.assertTrue(response.contains("milk") && response.contains("85.500"), "Stock unavailable");
	}

	@Test(description = "Stock availability for day 2", priority = 1)
	public void test_checkStock_day_2() {
		String response = given().basePath(TestStockDay2).get().asString();
		Assert.assertTrue(response.contains("milk") && response.contains("170.907"), "Stock unavailable");
	}

	@Test(description = "Stock reset", priority = 2)
	public void test_stock_reset() {
		RequestSpecification request = given().basePath(ReloadHerd);
		String expected = request.get().asString();
		Assert.assertEquals("Herd.Reloaded", expected, "Stock Reset Not Done");
	}

	@Test(description = "Negative scenario - Test stock with invalid request", priority = 3)
	public void test_stock_with_invalid_day() {
		int statuscode = given().basePath(TestStockwithInvalidDay).get().then().assertThat().contentType("text/html")
				.extract().statusCode();
		Assert.assertEquals(statuscode, 404, "Inavlid request");
	}

}
