package com.test.services;

import static io.restassured.RestAssured.given;
import static utility.Constants.Herd;
import static utility.Constants.ReloadHerd;
import static utility.Constants.TestStockDay1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

/*
 *  Heard Availability
 */
public class TestHerd extends TestBase {

	@Test(description = "Herd availability", priority = 0)
	public void test_herd_availability() {
		RequestSpecification request = given().basePath(Herd);
		String response = request.get().asString();
		Assert.assertTrue(response.contains("Betty-1") && response.contains("Betty-1"), "Herd data not available");
	}

	@Test(description = "Herd Reload", priority = 1)
	public void test_reloadHerd() {
		RequestSpecification request = given().basePath(ReloadHerd);
		String expected = request.get().asString();
		Assert.assertEquals("Herd.Reloaded", expected,"Herd not reloaded OR Invalid request");
	}

	@Test(description = "Validate Reload", priority = 2)
	public void test_herd_reset() {
		RequestSpecification request = given().basePath(TestStockDay1);
		String response = request.get().asString();
		Assert.assertTrue(response.contains("milk") && response.contains("85.500"),"Herd Reset Not Done OR Invalid request");
	}

}
