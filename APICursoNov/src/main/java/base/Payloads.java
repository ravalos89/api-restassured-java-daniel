package base;

public class Payloads {
	
	public static String payloadComplex(){		
		return "{\n"
				+ "	\"dashboard\":{\n"
				+ "		\"totalAmount\": 7290,\n"
				+ "		\"website\": \"books.com\"\n"
				+ "	},\n"
				+ "	\"books\":[\n"
				+ "		{\n"
				+ "			\"title\":\"Principito\",\n"
				+ "			\"price\": 50,\n"
				+ "			\"copies\": 6\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"El arte de la guerra\",\n"
				+ "			\"price\": 40,\n"
				+ "			\"copies\": 6\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"Pedro Paramo\",\n"
				+ "			\"price\": 45,\n"
				+ "			\"copies\": 10\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"La Biblia\",\n"
				+ "			\"price\": 70,\n"
				+ "			\"copies\": 90\n"
				+ "		}\n"
				+ "\n"
				+ "	]\n"
				+ "}";			
	}
	
	public static String addBookPayload() {
		return "{\n"
		  		+ "    \"name\":\"100 años de soledad\",\n"
		  		+ "    \"isbn\": \"DANIEL\",\n"
		  		+ "    \"aisle\": \"145678\",\n"
		  		+ "    \"author\":\"Gabriel Garcia Marquez\"\n"
		  		+ "}";
	}
	
	//Method overloaded
	public static String addBookPayload(String isbn, String aisle) {
		return "{\n"
		  		+ "    \"name\":\"100 años de soledad\",\n"
		  		+ "    \"isbn\": \""+isbn+"\",\n"
		  		+ "    \"aisle\": \""+aisle+"\",\n"
		  		+ "    \"author\":\"Gabriel Garcia Marquez\"\n"
		  		+ "}";
	}

}
