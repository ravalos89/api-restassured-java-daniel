package com.googlemaps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TC001_Validate_Place_ID_Add_Place_Service {
	public static void main(String[] args) {

		// Step 1 - Add Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlacePayload()).when().post("maps/api/place/add/json")

				// Validation Response
				.then().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().asString();
		
		// Step 2 - Validate execution is getting place_id		
		System.out.println(response);
		
		JsonPath jp = new JsonPath(response);
		String placeId = jp.getString("place_id");
		System.out.println(placeId);
		
		boolean validation;
		if(placeId!="") {
			validation = true;
		}else {
			validation=false;
		}
		
		Assert.assertTrue(validation);
	}
}
