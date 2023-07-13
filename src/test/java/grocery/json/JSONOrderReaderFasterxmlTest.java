package grocery.json;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import grocery.model.Order;
import grocery.model.OrderLine;

public class JSONOrderReaderFasterxmlTest {
	
	private static final String JSON_FILE="in_orders.txt";
	private static final URL URL = JSONReaderFasterxmlTest.class.getClassLoader().getResource(JSON_FILE);
	private static final String FILE_NAME = URL.getFile();
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void testFasterxml() throws StreamReadException, DatabindException, IOException {
		Path filePath = Paths.get(FILE_NAME.substring(1));
		byte[] jsonData = Files.readAllBytes(filePath.toAbsolutePath());
	
		System.out.println("===================================\nOrders \n"+new String(jsonData));

		
		JsonNode jsonNode = objectMapper.reader().readTree(jsonData);//.createObjectNode();
		System.out.println("===================================\nJSON Node Orders: "+jsonNode.toString());
		
		Order[] orders = objectMapper.readValue(jsonData, Order[].class);
		
		System.out.println("Order Objects\n"+orders);
		for (Order order: orders) {
			System.out.println(order);
		}
	}
	
	@Test
	public void testFasterxml2() throws JsonMappingException, JsonProcessingException {
		String jsonString = "{\"id\": 2, \"orderId\": 2, \"productId\": 5, \"purchasePrice\": 150.0, \"quantity\": 2}";
		JsonNode jsonNode = objectMapper.reader().readTree(jsonString);
		System.out.println(jsonNode);
		OrderLine orderLine = objectMapper.readValue(jsonString, OrderLine.class);
		System.out.println(orderLine);

	}
	
	@Test
	public void testFasterxml3() throws JsonMappingException, JsonProcessingException {
		String jsonString = "[{\"id\":2, \"orderId\":2,\"productId\":5,\"purchasePrice\":150.0,\"quantity\":2},{\"id\":4,\"orderId\":2,\"productId\":7,\"purchasePrice\":90.0,\"quantity\":1},{\"id\":5,\"orderId\":2,\"productId\":6,\"purchasePrice\":200.0,\"quantity\":6}]";
		OrderLine[] orderLines = objectMapper.readValue(jsonString, OrderLine[].class);
		for (OrderLine orderLine: orderLines) {
			System.out.println(orderLine);
		}

	}
}
