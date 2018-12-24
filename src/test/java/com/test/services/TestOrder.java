package com.test.services;

import static io.restassured.RestAssured.given;
import static utility.Constants.BASE_URL;
import static utility.Constants.Order1;
import static utility.Constants.ReloadHerd;
import static utility.Constants.TestStockDay1;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

/*
 * Validating customer orders [milk,skin] day wise and verifying stock changes
 */
@SuppressWarnings("deprecation")
public class TestOrder extends TestBase {

	private HttpClient client;

	@SuppressWarnings("resource")
	@Test(description = "Order Milk and Skins Day 1", priority = 0)
	public void test_order_milk_day_1() throws ClientProtocolException, IOException {
		String url = BASE_URL + Order1;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.addHeader("Accept", "application/json");
		HttpResponse response = client.execute(request);

		int statuscode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 201, "Order Incomplete");

		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		Assert.assertEquals((float) 10.0, ((float) object.getFloat("milk")), "Order Incomplete");
		Assert.assertEquals((float) 1, ((float) object.getFloat("skins")), "Order Incomplete");
	}

	@Test(description = "Stock availability after order", priority = 1)
	public void test_stock_availability_day_1() {
		String response = given().basePath(TestStockDay1).get().asString();
		Assert.assertTrue(response.contains("milk") && response.contains("75.500"), "Stock unavailable or Invalid request");
		Assert.assertTrue(response.contains("skins") && response.contains("2"), "Stock unavailable or Invalid request");
	}

	@Test(description = "Order Milk and Skins Day 2", priority = 2)
	public void test_order_milk_day_2() throws ClientProtocolException, IOException {
		String url = BASE_URL + Order1;
		client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.addHeader("Accept", "application/json");
		HttpResponse response = client.execute(request);

		int statuscode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 201, "Order Complete");

		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		Assert.assertEquals((float) 10.0, ((float) object.getFloat("milk")), "Order Incomplete");
	}

	@Test(description = "Stock availability after order", priority = 3)
	public void test_stock_availability_day_2() {
		String response = given().basePath(TestStockDay1).get().asString();
		System.out.println("Response :" + response);
		Assert.assertTrue(response.contains("milk") && response.contains("65.500"), "Stock unavailable or Invalid request");
		Assert.assertTrue(response.contains("skins") && response.contains("1"), "Stock unavailable or Invalid request");
	}

	@Test(description = "Stock reset", priority = 4)
	public void test_stock_reset() {
		RequestSpecification request = given().basePath(ReloadHerd);
		String expected = request.get().asString();
		Assert.assertEquals("Herd.Reloaded", expected, "Stock Reset Not Done");
	}

}
