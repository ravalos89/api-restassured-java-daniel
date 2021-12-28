package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class SpecBuilders {
	
	// REQUEST
	public static RequestSpecification requestAddBook(Object serializerObject) {
		RequestSpecification req = new RequestSpecBuilder().setBaseUri(GlobalVariables.API_BOOKS_ENDPOINT)
				.addHeader(GlobalVariables.LIBRARY_HEADER_CONTENT_TYPE_KEY, GlobalVariables.LIBRARY_HEADER_CONTENT_TYPE_VALUE).setBody(serializerObject).build();
		RequestSpecification startAPI = given().spec(req);
		return startAPI;
	}
	
	// RESPONSE
	public static Response postGetResponse(RequestSpecification requestSpec, String resource) {
		return requestSpec.expect().defaultParser(Parser.JSON).when().post(resource);
	}

}
