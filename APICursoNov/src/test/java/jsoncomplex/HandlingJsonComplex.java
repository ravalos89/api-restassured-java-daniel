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

	}

}
