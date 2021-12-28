package serialization;

import org.testng.annotations.Test;

import base.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import pojo.AddBookRequest;
import pojo.AddBookResponse;
import pojo.Dashboard;
import pojo.LibraryResponse;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class AddBookDeserialization {
	private String name, isbn, aisle, author;

	@BeforeTest
	public void beforeTest() {
		
		//DATA
		this.name="Harry Potter";
		this.isbn="TPO";
		this.aisle="8765";
		this.author="JK Rowling";
	}

	@Test
	public void deserialization() {
		
		// SERIALIZATION
		
		// Crear un objeto
		AddBookRequest abr = new AddBookRequest();
		abr.setName(name);
		abr.setIsbn(isbn);
		abr.setAisle(aisle);
		abr.setAuthor(author);

		RestAssured.baseURI = GlobalVariables.API_BOOKS_ENDPOINT;
		
		// DESERIALIZATION
		
		AddBookResponse response = given().header("Content-Type", "application/json")
		.body(abr)
		.expect().defaultParser(Parser.JSON)
		.when().post("Library/Addbook.php")
		.as(AddBookResponse.class);
		
		System.out.println(response);
		
		System.out.println(response.getMsg());
		System.out.println(response.getId());
		
		// Validate ID
		Assert.assertEquals(response.getId(), isbn+aisle);
		
		// EXAMPLE NESTED JSON
		
		Dashboard dashboard = new Dashboard();
		dashboard.setTotalAmount(400);
		dashboard.setWebsite("www");
		
		LibraryResponse library = new LibraryResponse();
		library.getDashbord();
		library.setDashbord(dashboard);

	}

}
