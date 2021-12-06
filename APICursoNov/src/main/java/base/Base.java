package base;

import io.restassured.path.json.JsonPath;

public class Base {
	
	/*
	 * Get value from Response
	 * @author
	 * @date
	 */
	public static String getValueFromResponseJson(String response, String value) {
		JsonPath js = new JsonPath(response);
		return js.getString(value);
	}

}
