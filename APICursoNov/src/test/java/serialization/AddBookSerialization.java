package serialization;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import base.GlobalVariables;
import io.restassured.RestAssured;
import pojo.AddBookRequest;

public class AddBookSerialization {
	
  @Test
  public void tc001() throws IOException {
	  
	  // Serialization
	  
	  // Crear un objeto
	  AddBookRequest abr = new AddBookRequest();
	  abr.setName("Harry Potter");
	  abr.setIsbn("KJH");
	  abr.setAisle("5454");
	  abr.setAuthor("JK Rowling");
	  
	  //Step 1 - Validate status code 200 when add new book.
	  String response = RestAssured.baseURI = GlobalVariables.API_BOOKS_ENDPOINT;
	  given().header("Content-Type", "application/json")
	  .body(abr)
	  .when().post("Library/Addbook.php")
	  .then().log().all().statusCode(200)
	  .extract().asString();
	  
	  System.out.println(response);
  }

}
