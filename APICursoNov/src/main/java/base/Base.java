package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;

public class Base {
	
	/*
	 * Get value from Response (1 node)
	 * @author
	 * @date
	 */
	public static String getValueFromResponseJson(String response, String value) {
		JsonPath js = new JsonPath(response);
		return js.getString(value);
	}
	
	/*
	 * Get value from Response (Overloaded) (2 node)
	 * @author
	 * @date
	 */
	public static String getValueFromResponseJson(String response, String node1, String node2) {
		JsonPath js = new JsonPath(response);
		return js.getString(node1+"."+node2);
	}
	
	/*
	 * Get String from Path Json(External Json)
	 * @author ricardo.avalos@automationsensei.com
	 */
	public static String getStringFromExternalJson(String jsonPath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(jsonPath)));
	}

}
