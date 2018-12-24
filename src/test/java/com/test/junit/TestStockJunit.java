package com.test.junit;

import static io.restassured.RestAssured.given;
import static utility.Constants.ReloadHerd;
import static utility.Constants.TestStockDay1;
import static utility.Constants.TestStockDay2;
import static utility.Constants.TestStockwithInvalidDay;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.specification.RequestSpecification;

/*
 *  Assert Stock availability by day wise and validating with request with positive and negative scenarios
 */
public class TestStockJunit extends TestBaseFromJunit{

	@Test
	public void test_checkStock_day_1() {
		String response = given().basePath(TestStockDay1).get().asString();
		Assert.assertTrue("Stock unavailable",response.contains("milk") && response.contains("85.500"));
	}

	@Test
	public void test_checkStock_day_2() {
		String response = given().basePath(TestStockDay2).get().asString();
		Assert.assertTrue("Stock unavailable", response.contains("milk") && response.contains("170.907"));
	}

	@Test
	public void test_stock_reset() {
		RequestSpecification request = given().basePath(ReloadHerd);
		String expected = request.get().asString();
		Assert.assertEquals("Stock Reset Not Done","Herd.Reloaded", expected);
	}

	@Test
	public void test_stock_with_invalid_day() {
		int statuscode = given().basePath(TestStockwithInvalidDay).get().then().assertThat().contentType("text/html")
				.extract().statusCode();
		Assert.assertEquals("Inavlid request",statuscode, 404);
	}

}
