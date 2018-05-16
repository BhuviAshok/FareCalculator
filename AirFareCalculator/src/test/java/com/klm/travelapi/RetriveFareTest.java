package com.klm.travelapi;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;


public class RetriveFareTest {

	private Response response = null; // Response object
	private JsonPath jsonPath = null; // JsonPath object

	@Before
	public void setup() {
		// Test Setup
		RestUtil.setBaseURI("http://localhost:9000/travel"); // Setup Base URI
		RestUtil.setBasePath(""); // Setup Base Path
		RestUtil.path = "retriveAirFare/AMS/OSL";
		RestUtil.setContentType(ContentType.JSON); // Setup Content Type
		response = RestUtil.getResponse(); // Get response
		jsonPath = RestUtil.getJsonPath(response); // Get JsonPath
	}

	@Test
	public void T01_StatusCodeTest() {
		HelperMethods.checkStatusIs200(response);
	}

	@Test
	public void T02_CheckOriginCode() {

		String origin = jsonPath.get("origin.code");
		assertTrue("Request for origin loc is correct!", origin.equalsIgnoreCase("AMS"));
	}

	@Test
	public void T03_CheckDestinationCode() {
		String dest = jsonPath.get("destination.code");
		assertTrue("Request for destination loc is correct!", dest.equalsIgnoreCase("OSL"));
	}

	@Test
	public void T04_CheckFareForEmpty() {
		Float fare = jsonPath.get("amount");
		assertTrue("Returned fare is not empty/black", fare != 0);
	}

	@After
	public void afterTest() {
		// Reset Values
		RestUtil.resetBaseURI();
		RestUtil.resetBasePath();
	}

}
