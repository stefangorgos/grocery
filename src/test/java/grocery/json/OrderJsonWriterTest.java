package grocery.json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.junit.Test;

import grocery.model.Order;
import grocery.model.OrderLine;

public class OrderJsonWriterTest {
	
	@Test
	public void testBuildOrder() throws FileNotFoundException {
		
		Order order = createOrder();

		
		JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
		JsonObjectBuilder orderLineBuilder = Json.createObjectBuilder();
		JsonArrayBuilder orderLineListBuilder = Json.createArrayBuilder();
		
		
		
		for(OrderLine orderLine : order.getOrderLines()) {
			orderLineListBuilder.add(orderLineBuilder.add("id", orderLine.getId())
					.add("orderId", orderLine.getOrderId())
					.add("productId", orderLine.getProductId())
					.add("purchasePrice", orderLine.getPurchasePrice())
					.add("quantity", orderLine.getQuantity()));
		}
		
		orderBuilder.add("id", order.getId())
			.add("customerId", order.getCustomerId())
			.add("customerName", order.getCustomerName());
			
		orderBuilder.add("orderLines", orderLineListBuilder);
		JsonObject orderJsonObject = orderBuilder.build();		
		
		System.out.println(orderJsonObject);
		
		OutputStream os = new FileOutputStream("src\\main\\resources\\out_orders.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		
		jsonWriter.writeObject(orderJsonObject);
		jsonWriter.close();
	}
	
	public static Order createOrder() {
		Order order = new Order();
		OrderLine orderLine = new OrderLine();
		order.setId(10);
		order.setCustomerId(2);
		order.setCustomerName("USM");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse("2023-05-01");
		} catch (ParseException e) {
			// do nothing by design
		}
		order.setDate(date);
		
		ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
		orderLine.setId(1);
		orderLine.setOrderId(order.getId());
		orderLine.setProductId(6);
		orderLine.setProductName("apa plata");
		orderLine.setPurchasePrice(300.00);
		orderLine.setQuantity(3);
		orderLines.add(orderLine);
		
		orderLine = new OrderLine();
		orderLine.setId(2);
		orderLine.setOrderId(order.getId());
		orderLine.setProductId(6);
		orderLine.setProductName("apa gazata");
		orderLine.setPurchasePrice(350.00);
		orderLine.setQuantity(3);
		orderLines.add(orderLine);
		
		order.setOrderLines(orderLines);
		
		return order;
	}

}
