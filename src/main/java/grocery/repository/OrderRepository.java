package grocery.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grocery.model.Order;
import grocery.model.OrderLine;

public class OrderRepository {
	private Connection connection;
	
	public OrderRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	
	public List<Order> getOrders() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select orders.id as order_id, customer_id, customers.name, date, \r\n"
				+ "order_lines.id as lines_id, order_lines.product_id, order_lines.purchase_price, \r\n"
				+ "order_lines.quantity \r\n"
				+ "from orders \r\n"
				+ "join order_lines on orders.id = order_lines.order_id \r\n"
				+ "join customers on customer_id = customers.id\r\n"
				+ "order by purchase_price asc");
		List<Object[]> resultSetAsList = new ArrayList<>();
		while (resultSet.next()) {
			Object[] row = new Object[8];
			for (int i=0; i < 7; i++) {
				row[i] = resultSet.getObject(i+1);
			}
			resultSetAsList.add(row);
		}
		
		for (int i = 0; i < resultSetAsList.size() - 1; i++) {
			for (int j = 0; j < resultSetAsList.size() - 1 - i; j++) {
				Object[] x = resultSetAsList.get(j);
				Object[] y = resultSetAsList.get(j+1);
				Integer id = (Integer)x[0];
				Integer id2 = (Integer)y[0];
				if(id > id2) {
					Object[] temp = resultSetAsList.get(j);
					resultSetAsList.set(j, resultSetAsList.get(j+1));
					resultSetAsList.set(j+1, temp);
				}
			}
		}	
		List<Order> orders = new ArrayList<>();
		Order order = null;
		OrderLine orderLine;
		Integer previousOrderId = -1;
		
		for(int i = 0; i < resultSetAsList.size() - 1; i++) {
			final Integer currentOrderId = (Integer)resultSetAsList.get(i)[0];
			if (!previousOrderId.equals(currentOrderId)) {
				previousOrderId = currentOrderId;
				order = new Order();
				order.setOrderLines(new ArrayList<OrderLine>());
				order.setId((Integer)resultSetAsList.get(i)[0]);
				order.setCustomerId((Integer)resultSetAsList.get(i)[1]);
				order.setCustomerName((String)resultSetAsList.get(i)[2]);
				order.setDate((Date)resultSetAsList.get(i)[3]);
				
				orders.add(order);
			}
			orderLine = new OrderLine();
			orderLine.setLines_id((Integer)resultSetAsList.get(i)[3]);
			orderLine.setProduct_id((Integer)resultSetAsList.get(i)[4]);
			orderLine.setPurchase_price(Double.valueOf(resultSetAsList.get(i)[5].toString()));
			orderLine.setQuantity((Integer)resultSetAsList.get(i)[6]);
			order.getOrderLines().add(orderLine);
					
		}

		return orders;
	}
	
	public List<OrderLine> getOrderLines(Integer orderId) throws SQLException {
		List<OrderLine> orderLines = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement("select order_lines.id, order_id, product_id, products.name, purchase_price, quantity \r\n"
				+ "from order_lines \r\n"
				+ "join products on product_id = products.id\r\n"
				+ "where order_id = ?");
		statement.setInt(1, orderId);
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		OrderLine orderLine;
		while(resultSet.next()) {
					
			orderLine = new OrderLine();
			orderLine.setId(resultSet.getInt("id"));
			orderLine.setOrderId(resultSet.getInt("order_id"));
			orderLine.setProductId(resultSet.getInt("product_id"));
			orderLine.setProductName(resultSet.getString("name"));
			orderLine.setPurchasePrice(resultSet.getDouble("purchase_price"));
			orderLine.setQuantity(resultSet.getInt("quantity"));
			orderLines.add(orderLine);
					
		}

		return orderLines;
	}
	
	public int createOrderDetails(Order order, OrderLine orderLine) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select id from orders where id = ?");
		statement.setInt(1, order.getId());
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, purchase_price, quantity) values(?,?,?,?)");
			statement2.setInt(1, orderLine.getOrderId());
			statement2.setInt(2, orderLine.getProductId());
			statement2.setDouble(3, orderLine.getPurchasePrice());
			statement2.setInt(4, orderLine.getQuantity());
			statement2.execute();
		} else {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO orders (customer_id, date) values(?,?)");
			statement2.setInt(1, order.getCustomerId());
			statement2.setDate(2, new java.sql.Date(order.getDate().getTime()));
			statement2.execute();

			PreparedStatement statement3 = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, purchase_price, quantity) values(?,?,?,?)");
			statement3.setInt(1, orderLine.getOrderId());
			statement3.setInt(2, orderLine.getProductId());
			statement3.setDouble(3, orderLine.getPurchasePrice());
			statement3.setInt(4, orderLine.getQuantity());
			statement3.execute();
		}
		

		return 0;
	}
	
	public Order createOrder(Order order) throws SQLException {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO orders (customer_id, date) values(?,?) RETURNING id");
			statement2.setInt(1, order.getCustomerId());
			statement2.setDate(2, new java.sql.Date(order.getDate().getTime()));
			statement2.execute();
			ResultSet resultSet = statement2.getResultSet();
			resultSet.next();
			order.setId(resultSet.getInt("id"));
		return order;
	}
	
	public Order getOrderById(int selectedOrderId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from orders\r\n"
				+ "join customers on orders.customer_id = customers.id\r\n"
				+ "where orders.id = ?");
		statement.setInt(1, selectedOrderId);
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		Order order = new Order();
		order.setId(resultSet.getInt("id"));
		order.setCustomerId(resultSet.getInt("customer_id"));
		order.setCustomerName(resultSet.getString("name"));
		order.setDate(resultSet.getDate("date"));	
		
		return order;
	}
}
