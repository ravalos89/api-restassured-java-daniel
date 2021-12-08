package apibooks;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import base.GlobalVariables;
import base.Payloads;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.restassured.RestAssured;

public class AddBook {
	
  @Test
  public void tc001() throws IOException {
	  
	  //Step 1 - Validate status code 200 when add new book.
	  RestAssured.baseURI = GlobalVariables.API_BOOKS_ENDPOINT;
	  given().header("Content-Type", "application/json")
	  .body(Base.getStringFromExternalJson(GlobalVariables.JSON_PATH_ADD_BOOK))
	  .when().post("Library/Addbook.php")
	  .then().log().all().statusCode(200);
  }
  
  @Test
  public void tc002() {
	  
	  // Step 1 - Add new book
	  RestAssured.baseURI = "http://216.10.245.166";
	  String response = given().header("Content-Type", "application/json")
	  .body(Payloads.addBookPayload("RIC", "WEED"))
	  .when().post("Library/Addbook.php")
	  .then().log().all().statusCode(200).extract().asString();
	  
	  // Step 2 - Validate payload response ID book.
	  String bookID = Base.getValueFromResponseJson(response, "ID");
	  System.out.println("Este es el book ID: "+bookID);
	  Assert.assertNotNull(bookID);
  }
  
  @Test(dataProvider="booksData")
  public void tc004(String isbn, String aisle) {
	  
	  // Step 1 - Add multiple books in the same request
	  RestAssured.baseURI = "http://216.10.245.166";
	  String response = given().header("Content-Type", "application/json")
	  .body(Payloads.addBookPayload(isbn, aisle))
	  .when().post("Library/Addbook.php")
	  .then().log().all().statusCode(200).extract().asString();
	  
	  // Step 2 - Validate each ID from response
	  String bookID = Base.getValueFromResponseJson(response, "ID");
	  System.out.println("Este es el book ID: "+bookID);
	  Assert.assertNotNull(bookID);
  }
  
  @DataProvider
  //Array = Collection of elements
  //Collection = Collection of arrays
  public Object[][] booksData(){
	  return new Object[][] {
		  {"RIC", "AGHT"},
		  {"DAN", "ALON"},
		  {"RTY", "1234"}
	  };
  }
}
