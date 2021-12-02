package com.googlemaps;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateAddPlace {

	public static void main(String[] args) {
		
		// Invocar un objeto tipo RestAssured
		
		// Validation Request
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.addPlacePayload()).when().post("maps/api/place/add/json")
		
		// Validation Response
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)");
	}

}
