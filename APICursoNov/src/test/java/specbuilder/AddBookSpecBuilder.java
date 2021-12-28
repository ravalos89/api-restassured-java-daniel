package specbuilder;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.GlobalVariables;
import base.SpecBuilders;
import io.restassured.response.Response;
import pojo.AddBookRequest;
import pojo.AddBookResponse;

public class AddBookSpecBuilder {
	private String name, isbn, aisle, author;

	@BeforeTest
	public void beforeTest() {

		// DATA (Excel, JSON, TXT)
		this.name = "Harry Potter";
		this.isbn = "TOPH";
		this.aisle = "8765";
		this.author = "JK Rowling";
	}

	@Test
	public void specBuilderTest() {

		// SERIALIZATION

		// Crear un objeto
		AddBookRequest abr = new AddBookRequest();
		abr.setName(name);
		abr.setIsbn(isbn);
		abr.setAisle(aisle);
		abr.setAuthor(author);

		// Request Spec Builder
		Response response = SpecBuilders.postGetResponse(SpecBuilders.requestAddBook(abr), GlobalVariables.LIBRARY_ADD_BOOK_RESOURCE);
		
		// Store Response into Object class (Deserealizaer)
		AddBookResponse responseObject = response.as(AddBookResponse.class);
		
		// Deserialization
		Assert.assertEquals(responseObject.getId(), isbn+aisle);

	}

}
