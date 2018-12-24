package com.test.junit;

import static io.restassured.RestAssured.given;
import static utility.Constants.Herd;
import static utility.Constants.ReloadHerd;
import static utility.Constants.TestStockDay1;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.specification.RequestSpecification;

/*
 *  Heard Availability
 */
public class TestHerdJunit extends TestBaseFromJunit {

	@Test
	public void test_herd_availability() {
		RequestSpecification request = given().basePath(Herd);
		String response = request.get().asString();
		Assert.assertTrue("Herd data not available",response.contains("Betty-1") && response.contains("Betty-1"));
	}

	@Test
	public void test_reloadHerd() {
		RequestSpecification request = given().basePath(ReloadHerd);
		String expected = request.get().asString();
		Assert.assertEquals("Herd not reloaded OR Invalid request","Herd.Reloaded", expected);
	}

	@Test
	public void test_herd_reset() {
		RequestSpecification request = given().basePath(TestStockDay1);
		String response = request.get().asString();
		Assert.assertTrue("Herd Reset Not Done OR Invalid request",response.contains("milk") && response.contains("85.500"));
	}

}
