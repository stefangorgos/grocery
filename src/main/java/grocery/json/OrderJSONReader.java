package grocery.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;


import grocery.model.Order;
import grocery.model.OrderLine;

public class OrderJSONReader {
	private static final String JSON_FILE="in_orders.txt";
	private static final URL URL = EmployeeJSONReader.class.getClassLoader().getResource(JSON_FILE);
	private static final String FILE_NAME = URL.getFile();
	
	public static void main(String[] args) throws IOException {

		InputStream fis = new FileInputStream(FILE_NAME);
		JsonReader jsonReader = Json.createReader(fis);

		JsonArray jsonArray = jsonReader.readArray();
		jsonReader.close();
		fis.close();

		List<Order> orders = new ArrayList<>();
		
		for (int i=0; i<jsonArray.size(); i++) {
			Order order = new Order();
			JsonObject jsonObject = (JsonObject) jsonArray.get(i);
			order.setId(jsonObject.getInt("id"));
			order.setCustomer_id(jsonObject.getInt("customer_id"));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = simpleDateFormat.parse(jsonObject.getString("date"));
			} catch (ParseException e) {
				// do nothing by design
			}
			order.setDate(date);
			order.setOrderLines(new ArrayList<OrderLine>());
			
			JsonArray innerJsonArray = jsonObject.getJsonArray("order_lines");
			for (int j=0; j<innerJsonArray.size();j++) {
				JsonObject innerJsonObject = (JsonObject) innerJsonArray.get(i);
				OrderLine orderLine = new OrderLine();
				orderLine.setLines_id(innerJsonObject.getInt("id"));
				orderLine.setOrder_id(jsonObject.getInt("id"));
				orderLine.setProduct_id(innerJsonObject.getInt("product_id"));
				final JsonNumber purchasePriceAsString = innerJsonObject.getJsonNumber("purchase_price");
				orderLine.setPurchase_price(purchasePriceAsString.doubleValue());
				orderLine.setQuantity(innerJsonObject.getInt("quantity"));
				order.getOrderLines().add(orderLine);
			}
			orders.add(order);
		}
	}
}
