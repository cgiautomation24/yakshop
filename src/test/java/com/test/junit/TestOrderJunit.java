package com.test.junit;

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
import org.junit.Assert;
import org.junit.Test;

import io.restassured.specification.RequestSpecification;

/*
 * Validating customer orders [milk,skin] day wise and verifying stock changes
 */
@SuppressWarnings("deprecation")
public class TestOrderJunit  extends TestBaseFromJunit{

	private HttpClient client;

	@SuppressWarnings("resource")
	@Test
	public void test_order_milk_day_1() throws ClientProtocolException, IOException {
		String url = BASE_URL + Order1;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.addHeader("Accept", "application/json");
		HttpResponse response = client.execute(request);

		int statuscode = response.getStatusLine().getStatusCode();
		Assert.assertEquals("Order Incomplete",statuscode, 201);

		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		Assert.assertEquals((double) 10.0, ((double) object.getFloat("milk")));
		Assert.assertEquals((double) 1, ((double) object.getFloat("skins")));
	}

	@Test
	public void test_stock_availability_day_1() {
		String response = given().basePath(TestStockDay1).get().asString();
		Assert.assertTrue(response.contains("milk") && response.contains("75.500"));
		Assert.assertTrue(response.contains("skins") && response.contains("2"));
	}

	@Test
	public void test_order_milk_day_2() throws ClientProtocolException, IOException {
		String url = BASE_URL + Order1;
		client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.addHeader("Accept", "application/json");
		HttpResponse response = client.execute(request);

		int statuscode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 201);

		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		Assert.assertEquals((float) 10.0, ((float) object.getFloat("milk")));
	}

	@Test
	public void test_stock_availability_day_2() {
		String response = given().basePath(TestStockDay1).get().asString();
		System.out.println("Response :" + response);
		Assert.assertTrue("Stock unavailable or Invalid request",response.contains("milk") && response.contains("65.500"));
		Assert.assertTrue("Stock unavailable or Invalid request",response.contains("skins") && response.contains("1"));
	}

	@Test
	public void test_stock_reset() {
		RequestSpecification request = given().basePath(ReloadHerd);
		String expected = request.get().asString();
		Assert.assertEquals("Stock Reset Not Done","Herd.Reloaded", expected);
	}

}
