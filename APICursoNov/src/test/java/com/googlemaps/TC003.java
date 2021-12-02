package com.googlemaps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TC003 {

	public static void main(String[] args) {
		
		// Step 1 - Add Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlacePayload()).when().post("maps/api/place/add/json")

				// Validation Response
				.then().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)")
				.extract().asString();
		
		JsonPath jp = new JsonPath(response);
		String placeId = jp.getString("place_id");
		System.out.println(placeId);
		
		// Step 2 - PUT update address
		String newAddress = "Irapuato, Guanajuato";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Step 3 - Verify address has been updated.
		// GET
		
		String getResponseBody = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath jp2 = new JsonPath(getResponseBody);
		String addressUpdated = jp2.getString("address");
		
		Assert.assertEquals(addressUpdated, newAddress);

	}

}
