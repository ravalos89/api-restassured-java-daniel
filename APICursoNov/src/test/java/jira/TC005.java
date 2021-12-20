package jira;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import base.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class TC005 {
  @Test
  public void tc005() {
	  
	  //Step 1 - Create new session using Login API (Cookie auth).
	  RestAssured.baseURI = GlobalVariables.JIRA_ENDPOINT;
	  
	  // SESSION FILTER
	  SessionFilter session = new SessionFilter();
	
	  given().header("Content-Type", "application/json")
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
	  
	  // Step 3 - Add txt attachment using /rest/api/2/issue/{issueIDorKey}/attachments
	  String fileName = "APIRESTASSURED.txt";
	  String responseAttach = given().header("X-Atlassian-Token", "no-check")
			  .pathParam("key", keyIssue)
			  .filter(session)
			  .multiPart("file", new File("/Users/ricardoavalos/git/api-restassured-java-daniel/APICursoNov/"+fileName))
			  .when().post("/rest/api/2/issue/{key}/attachments")
			  .then().log().all().assertThat().statusCode(200).extract().response().asString();
	  
	  // Step 4 - Validate attachment was uploaded successfully
	  String uploadedFile = Base.getValueFromResponseJson(responseAttach, "[0]", "filename");
	  Assert.assertEquals(uploadedFile, fileName);
  }
}
