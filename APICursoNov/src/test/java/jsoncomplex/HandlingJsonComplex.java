package jsoncomplex;

import base.Payloads;
import io.restassured.path.json.JsonPath;

public class HandlingJsonComplex {

	public static void main(String[] args) {
		
		JsonPath jp = new JsonPath(Payloads.payloadComplex());
		
		// Ejercicio 1 Obtener Total amount
		int totalAmount = jp.getInt("dashboard.totalAmount");
		System.out.println("El total amount es: "+ totalAmount);
		
		// Ejercicio 2 Cantidad de libros en existencia de el nodo Books
		int books = jp.getInt("books.size()");
		System.out.println("el nodo books tiene : "+books+ " libros");
		
		//Ejercicio3 - Imprimir segundo Libro del nodo "Books"
		String secondBook = jp.getString("books[1].title");
		System.out.println("El nombre del segundo libro es: "+secondBook);
		
		//Ejercicio4 - 
		int price = jp.getInt("books[2].price");
		System.out.println("El Precio del tercer libro es:"+price);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		// Ejercicio 5 Imprimir TODA la informacion
		for(int i=0; i<books;i++) {
			System.out.println("El nombre del libro #"+(i+1)+" es " + jp.getString("books["+i+"].title"));
			System.out.println("El precio del libro es " + jp.getInt("books["+i+"].price"));
			System.out.println("las copias vendidas son " + jp.getInt("books["+i+"].copies"));
		}

	}

}
