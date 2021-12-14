package jira;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import base.GlobalVariables;
import io.restassured.RestAssured;

public class TC001 {
	@Test
  public void tc001() {
	  
	  RestAssured.baseURI = GlobalVariables.JIRA_ENDPOINT;
	  
	  // Login cookie-based authentication
	  given().header("Content-Type", "application/json")
	  .body("{ \n"
	  		+ "    \"username\": \"ricardo.avalos\", \n"
	  		+ "    \"password\": \"PuwSNrGF74!feDJ\" \n"
	  		+ "}")
	  .when().post("/rest/auth/1/session")
	  .then().log().all().assertThat().statusCode(200);
	  
  }
}
