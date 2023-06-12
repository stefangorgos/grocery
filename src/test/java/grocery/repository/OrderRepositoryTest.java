package grocery.repository;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import grocery.model.Order;
import grocery.model.OrderLines;

public class OrderRepositoryTest {
	
	@Test
	public void testReadorders() throws SQLException {
		OrderRepository orderRepository = new OrderRepository();
		List<Order> orders = orderRepository.getOrders();
		assertTrue(orders.size() > 0);
	}

	@Test
	public void testCreateOrderDetails() throws SQLException {
		OrderRepository orderRepository = new OrderRepository();
		Order order = new Order();
		OrderLines orderLines = new OrderLines();
		order.setId(25);
		order.setCustomer_id(4);
		order.setDate(null);
		
		orderLines.setOrder_id(order.getId());
		orderLines.setProduct_id(6);
		orderLines.setPurchase_price(300.00);
		orderRepository.createOrderDetails(order, orderLines);
	}
}
