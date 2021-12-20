package jira;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import base.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class TC004Loop {
  @Test
  public void tc004() {
	  
//	  Step 1 - Create new session using Login API (Cookie auth).
	  RestAssured.baseURI = GlobalVariables.JIRA_ENDPOINT;
	  
	  // SESSION FILTER
	  SessionFilter session = new SessionFilter();
	  
	  // Step 1 - Create new session Login cookie-based authentication
	  given().header("Content-Type", "application/json")
	  .body("{ \n"
	  		+ "    \"username\": \"ricardo.avalos\", \n"
	  		+ "    \"password\": \"PuwSNrGF74!feDJ\" \n"
	  		+ "}")
	  .filter(session)
	  .when().post("/rest/auth/1/session")
	  .then().log().all().assertThat().statusCode(200);
	  
	  String keyIssue = "API-19";
	  String idComment = "10206";
	  
//	  Step 5 - Update comment
	  String expectedComment = "Here the comments has been updated successfully - RESTASSURED";
	  String responseUpdateComment = given().filter(session)
	  .pathParam("idComment", idComment)
	  .pathParam("keyIssue", keyIssue)
	  .queryParam("fields", "comment")
	  .header("Content-Type", "application/json")
	  .body("{\n"
	  		+ "    \"body\": \""+expectedComment+"\",\n"
	  		+ "    \"visibility\": {\n"
	  		+ "        \"type\": \"role\",\n"
	  		+ "        \"value\": \"Administrators\"\n"
	  		+ "    }\n"
	  		+ "}")
	  .when().put("/rest/api/2/issue/{keyIssue}/comment/{idComment}")	
	  .then().log().all()
	  .assertThat().statusCode(200)
	  .extract().response().asString();
	  
//	  Step 6 - Verify update comment
	  String updatedComment = Base.getValueFromResponseJson(responseUpdateComment, "body");
	  Assert.assertEquals(updatedComment, expectedComment);
  }
}
