package jira;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import base.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class TC006 {
  @Test
  public void tc006() {
	  
	  //Step 1 - Create new session using Login API (Cookie auth).
	  RestAssured.baseURI = GlobalVariables.JIRA_ENDPOINT;
	  
	  // SESSION FILTER
	  SessionFilter session = new SessionFilter();
	
	  given().relaxedHTTPSValidation().header("Content-Type", "application/json")
	  .body("{ \n"
	  		+ "    \"username\": \"ricardo.avalos\", \n"
	  		+ "    \"password\": \"PuwSNrGF74!feDJ\" \n"
	  		+ "}")
	  .filter(session)
	  .when().post("/rest/auth/1/session")
	  .then().log().all().assertThat().statusCode(200);
	  
	  //Step 2 - Add new issue to Jira.
	  String responseCreateIssue=given().header("Content-Type", "application/json")
	  .body("{\n"
	  		+ "    \"fields\": {\n"
	  		+ "       \"project\":\n"
	  		+ "       {\n"
	  		+ "          \"key\": \"API\"\n"
	  		+ "       },\n"
	  		+ "       \"summary\": \"Attachments\",\n"
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
	  
	  String keyIssue = Base.getValueFromResponseJson(responseCreateIssue, "key");
	  
//	  Step 3 - Delete issue
	  given()
	  .pathParam("issueID", keyIssue)
	  .filter(session)
	  .when().delete("/rest/api/2/issue/{issueID}")
	  .then().log().all().assertThat().statusCode(204);
	  
//	  Step 4 - Validate issue was removed successfully
	  String responseDelete = given()
	  .pathParam("getIssueID", keyIssue)
	  .filter(session)
	  .when().get("/rest/api/2/issue/{getIssueID}")
	  .then().log().all()
	  .assertThat().statusCode(404)
	  .extract().response().asString();
	  
	  JsonPath js = new JsonPath(responseDelete);
	  String message = js.getString("errorMessages");
	  System.out.println(message);
	  Assert.assertNotNull(message);
  }
}
