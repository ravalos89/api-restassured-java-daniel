package base;

public class Payloads {
	
	public static String payloadComplex(){		
		return "{\n"
				+ "	\"dashboard\":{\n"
				+ "		\"totalAmount\": 910,\n"
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

}
