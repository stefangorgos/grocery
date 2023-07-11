package grocery.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import org.junit.Test;

public class OrderJSONReaderTest {
	
	private static final String JSON_FILE="in_orders.txt";
	private static final URL URL = EmployeeJSONReader.class.getClassLoader().getResource(JSON_FILE);
	private static final String FILE_NAME = URL.getFile();
	
	@Test
	public void testJsonStructure() throws IOException {
		InputStream fis = new FileInputStream(FILE_NAME);
		JsonReader jsonReader = Json.createReader(fis);
		JsonStructure jsonStrunvture = jsonReader.read();
		jsonReader.close();
		fis.close();
		
		JsonArray jsArr = (JsonArray) jsonStrunvture;
		for (int i=0; i<jsArr.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsArr.get(i);
//			System.out.printf("i=%s: id=%s, customer_id=%s, date=%s", i, jsonObject.get("id"), jsonObject.get("customer_id"), jsonObject.get("date"));
			System.out.println(jsonObject);
		}
	}

}
