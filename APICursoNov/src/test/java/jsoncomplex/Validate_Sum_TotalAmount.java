package jsoncomplex;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Payloads;
import io.restassured.path.json.JsonPath;

public class Validate_Sum_TotalAmount {
	
  @Test
  public void ejercicio6() {
	  
	  JsonPath jp = new JsonPath(Payloads.payloadComplex());
		
		// Total amount
		int expectedTotalAmount = jp.getInt("dashboard.totalAmount");
		System.out.println("El total amount es: "+ expectedTotalAmount);
		
		// Sum del precio final de todos los libros
		int sumTotal=0;
		int books = jp.getInt("books.size()");
		for(int i=0; i<books;i++) {
			// Get prices and copies
			int priceBook = jp.getInt("books["+i+"].price");
			int copiesSelledBook = jp.getInt("books["+i+"].copies");
			
			// Multiplicacion
			int mult = priceBook*copiesSelledBook;
			
			//Sum Total
			sumTotal = sumTotal+mult;
		}
		
		System.out.println("La suma total de las ventas es: "+sumTotal);
		
		Assert.assertEquals(sumTotal, expectedTotalAmount);
  }
}
