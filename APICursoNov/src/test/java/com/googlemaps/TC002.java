package com.googlemaps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import base.Base;
import io.restassured.RestAssured;

public class TC002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Step 1 - Add Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlacePayload()).when().post("maps/api/place/add/json")

				// Validation Response
				.then().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)")
				.extract().asString();

		// Step 2 - Validate execution is getting place_id
		System.out.println(response);

//		JsonPath jp = new JsonPath(response);
//		String Status = jp.getString("status");
//		System.out.println(Status);
		
		String Status = Base.getValueFromResponseJson(response, "status");

		boolean validation;
		if (Status.equals("OK")) {
			validation = true;
		} else {
			validation = false;
		}

		Assert.assertTrue(validation);

	}
}
