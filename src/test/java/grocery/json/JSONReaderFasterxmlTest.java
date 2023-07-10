package grocery.json;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReaderFasterxmlTest {
	private static final String JSON_FILE="in_employee.txt";
	private static final URL URL = JSONReaderFasterxmlTest.class.getClassLoader().getResource(JSON_FILE);
	private static final String FILE_NAME = URL.getFile();
	
	@Test
	public void testFasterxml() throws StreamReadException, DatabindException, IOException {
		Path filePath = Paths.get(FILE_NAME.substring(1));
		byte[] jsonData = Files.readAllBytes(filePath.toAbsolutePath());
		
		System.out.println("===================================\nJSON Employees \n"+new String(jsonData));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonNode = objectMapper.reader().readTree(jsonData);//.createObjectNode();
		System.out.println("===================================\nJSON Node Employees: "+jsonNode.toString());
		
		//convert json string to object
		Employee[] emps = objectMapper.readValue(jsonData, Employee[].class);
		
		System.out.println("Employee Objects\n"+emps);
		for (Employee emp: emps) {
			System.out.println(emp);
		}
	}
}
