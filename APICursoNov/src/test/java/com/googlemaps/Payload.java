package com.googlemaps;

public class Payload {
	
	public static String addPlacePayload() {
		return "{\n"
				+ "    \"location\": {\n"
				+ "        \"lat\": -38.383494,\n"
				+ "        \"lng\": 33.427362\n"
				+ "    },\n"
				+ "    \"accuracy\": 50,\n"
				+ "    \"name\": \"Cinepolis Leon\",\n"
				+ "    \"phone_number\": \"(+91) 811 7676\",\n"
				+ "    \"address\": \"Leon, Guanajuato\",\n"
				+ "    \"types\": [\n"
				+ "        \"shoe park\",\n"
				+ "        \"shop\"\n"
				+ "    ],\n"
				+ "    \"website\": \"http://cinepolis.com\",\n"
				+ "    \"language\": \"French-IN\"\n"
				+ "}";
	}

}
