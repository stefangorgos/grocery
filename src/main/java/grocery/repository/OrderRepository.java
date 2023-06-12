package grocery.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grocery.model.Order;
import grocery.model.OrderLines;

public class OrderRepository {
	private Connection connection;
	
	public OrderRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	
	public List<Order> getOrders() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select orders.id as order_id, customer_id, date, order_lines.id as lines_id, order_lines.product_id, order_lines.purchase_price from orders join order_lines on orders.id = order_lines.order_id");
		List<Order> orders = new ArrayList<>();
		Order order = null;
		OrderLines orderLines;
		Integer previousOrderId = -1;
		
		while (resultSet.next()) {
			final Integer currentOrderId = resultSet.getInt("order_id");
			if (!previousOrderId.equals(currentOrderId)) {
				previousOrderId = currentOrderId;
				order = new Order();
				order.setOrderLines(new ArrayList<OrderLines>());
				order.setId(resultSet.getInt("order_id"));
				order.setCustomer_id(resultSet.getInt("customer_id"));
				order.setDate(resultSet.getDate("date"));
				
				orders.add(order);
			}
			orderLines = new OrderLines();
			orderLines.setLines_id(resultSet.getInt("lines_id"));
			orderLines.setProduct_id(resultSet.getInt("product_id"));
			orderLines.setPurchase_price(resultSet.getDouble("purchase_price"));
			order.getOrderLines().add(orderLines);
					
		}

		return orders;
	}
	
	public int createOrderDetails(Order order, OrderLines orderLines) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select id from orders where id = ?");
		statement.setInt(1, order.getId());
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, purchase_price) values(?,?,?)");
			statement2.setInt(1, orderLines.getOrder_id());
			statement2.setInt(2, orderLines.getProduct_id());
			statement2.setDouble(3, orderLines.getPurchase_price());
			statement2.execute();
		} else {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO orders (customer_id, date) values(?,?)");
			statement2.setInt(1, order.getCustomer_id());
			statement2.setDate(2, order.getDate());
			statement2.execute();

			PreparedStatement statement3 = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, purchase_price) values(?,?,?)");
			statement3.setInt(1, orderLines.getOrder_id());
			statement3.setInt(2, orderLines.getProduct_id());
			statement3.setDouble(3, orderLines.getPurchase_price());
			statement3.execute();
		}
		

		return 0;
	}
}
