package jira;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import base.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class TC004 {
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
	  
//	  Step 2 - Add new issue to Jira.
	  String responseCreateIssue=given().header("Content-Type", "application/json")
	  .body("{\n"
	  		+ "    \"fields\": {\n"
	  		+ "       \"project\":\n"
	  		+ "       {\n"
	  		+ "          \"key\": \"API\"\n"
	  		+ "       },\n"
	  		+ "       \"summary\": \"TC004 Issue\",\n"
	  		+ "       \"description\": \"Description from API Rest assured\",\n"
	  		+ "       \"issuetype\": {\n"
	  		+ "          \"name\": \"Bug\"\n"
	  		+ "       }\n"
	  		+ "   }\n"
	  		+ "}")
	  .filter(session)
	  // RESOURCE o Recurso
	  .when().post("/rest/api/2/issue")
	  .then().log().all()
	  .assertThat().statusCode(201).extract().response().asString();
	  
//	  Step 3 - Add new comment to recently jira issue created.
	  String keyIssue = Base.getValueFromResponseJson(responseCreateIssue, "key");
	  
	  // Step 3 - Add new comment to recently jira issue created.
	  String newComment ="Comment #1 added from RestAssured";
	  String responseAddComment = given()
	  .pathParam("ricardo", keyIssue)
	  .header("Content-Type", "application/json")
//	  .header("Cookie", headerValue)
	  .body("{\n"
	  		+ "    \"body\": \""+newComment+"\",\n"
	  		+ "    \"visibility\": {\n"
	  		+ "        \"type\": \"role\",\n"
	  		+ "        \"value\": \"Administrators\"\n"
	  		+ "    }\n"
	  		+ "}")
	  .filter(session)
	  .when().post("/rest/api/2/issue/{ricardo}/comment")
	  .then().log().all()
	  .assertThat().statusCode(201).extract().response().asString();
	  
//	  Step 4 - Verify comment was added successfully
	  
	  // Get Message comment
	  String idComment = Base.getValueFromResponseJson(responseAddComment, "id");
		  
	  // Step 4 - Verify comment was added successfully
	  Assert.assertNotNull(idComment);
	  
//	  Step 5 - Update comment
	  String expectedComment = "Here the comments has been updated successfully - RESTASSURED";
	  String responseUpdateComment = given().filter(session)
	  .pathParam("idComment", idComment)
	  .pathParam("keyIssue", keyIssue)
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
