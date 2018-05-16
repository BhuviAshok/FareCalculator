package com.klm.travelapi;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.klm.travelapi.model.Location;



public class AiportListTest {

	private Response response = null; // Response object
	private JsonPath jsonPath = null; // JsonPath object

	@Before
	public void setup() {
		// Test Setup
		RestUtil.setBaseURI("http://localhost:9000/travel"); // Setup Base URI
		RestUtil.setBasePath(""); // Setup Base Path
		RestUtil.path = "airports";
		RestUtil.setContentType(ContentType.JSON); // Setup Content Type
		response = RestUtil.getResponse(); // Get response
		jsonPath = RestUtil.getJsonPath(response); // Get JsonPath
	}

	@Test
	public void T01_StatusCodeTest() {
		HelperMethods.checkStatusIs200(response);
	}

	@Test
	public void T02_LocationCountVerify() {
		assertTrue("Location count is incorrect!", getLocationListCount(response) == 25);
	}

	// Get location count (For use case-2)
	public int getLocationListCount(Response resp) {

		ArrayList<Location> relatedVideoList = jsonPath.get("parent");
		return relatedVideoList.size();
	}

	@After
	public void afterTest() {
		// Reset Values
		RestUtil.resetBaseURI();
		RestUtil.resetBasePath();
	}

}
