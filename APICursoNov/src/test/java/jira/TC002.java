package jira;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import base.GlobalVariables;
import io.restassured.RestAssured;

public class TC002 {
  @Test
  public void tc002() {
	  
	  RestAssured.baseURI = GlobalVariables.JIRA_ENDPOINT;
	  
	  // Step 1 - Create new session Login cookie-based authentication
	  String responseSession = given().header("Content-Type", "application/json")
	  .body("{ \n"
	  		+ "    \"username\": \"ricardo.avalos\", \n"
	  		+ "    \"password\": \"PuwSNrGF74!feDJ\" \n"
	  		+ "}")
	  .when().post("/rest/auth/1/session")
	  .then().log().all().assertThat().statusCode(200).extract().response().asString();
	  
	  // Step 2 - Validate name and value session are not null.
	  String nameSession = Base.getValueFromResponseJson(responseSession, "session", "name");
	  String valueSession = Base.getValueFromResponseJson(responseSession, "session", "value");
	  
	  System.out.println("SESSION NAME = "+nameSession);
	  System.out.println("SESSION VALUE = "+valueSession);
	  
	  // Validation
	  Assert.assertNotNull(nameSession);
	  Assert.assertNotNull(valueSession);
	 	  
  }
}
