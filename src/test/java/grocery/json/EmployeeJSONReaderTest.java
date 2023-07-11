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

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeJSONReaderTest {
	private static final String JSON_FILE="in_employee.txt";
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
			System.out.printf("i=%s: id=%s, address=%s", i, jsonObject.get("id"),  ((JsonObject) jsonObject.get("address")).get("street"));
			System.out.println();
//			System.out.printf("\tkeys: %s, values: %s", jsonObject.get("id"), jsonObject.asJsonObject().values());
//			System.out.println();
		}
	}
	
	@Test
	public void testJsonArray() throws IOException {
		InputStream fis = new FileInputStream(FILE_NAME);
		JsonReader jsonReader = Json.createReader(fis);
		JsonArray jsArr = jsonReader.readArray();
		jsonReader.close();
		fis.close();
		
		for (int i=0; i<jsArr.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsArr.get(i);
			System.out.printf("i=%s: id=%s, address=%s", i, jsonObject.get("id"),  jsonObject.get("address"));
			System.out.println();
			System.out.printf("\tkeys: %s, values: %s", jsonObject.get("id"), jsonObject.asJsonObject().values());
			System.out.println();
		}
	}
	
	@Test
	public void testJsonArrayConvert() throws IOException {
		InputStream fis = new FileInputStream(FILE_NAME);
		JsonReader jsonReader = Json.createReader(fis);
		JsonArray jsArr = jsonReader.readArray();
		jsonReader.close();
		fis.close();
		
		ObjectMapper jsonMapper = new ObjectMapper();
		
		for (int i=0; i<jsArr.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsArr.get(i);
			Employee employee = jsonMapper.readValue(jsonObject.toString(), Employee.class);
			System.out.println(employee);
		}
	}
}
