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
import grocery.model.OrderLines;

public class OrderRepository {
	private Connection connection;
	
	public OrderRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	
	public List<Order> getOrders() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select orders.id as order_id, customer_id, date, order_lines.id as lines_id, order_lines.product_id, order_lines.purchase_price, order_lines.quantity from orders join order_lines on orders.id = order_lines.order_id order by purchase_price asc");
		List<Object[]> resultSetAsList = new ArrayList<>();
		while (resultSet.next()) {
			Object[] row = new Object[7];
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
		OrderLines orderLines;
		Integer previousOrderId = -1;
		
		for(int i = 0; i < resultSetAsList.size() - 1; i++) {
			final Integer currentOrderId = (Integer)resultSetAsList.get(i)[0];
			if (!previousOrderId.equals(currentOrderId)) {
				previousOrderId = currentOrderId;
				order = new Order();
				order.setOrderLines(new ArrayList<OrderLines>());
				order.setId((Integer)resultSetAsList.get(i)[0]);
				order.setCustomer_id((Integer)resultSetAsList.get(i)[1]);
				order.setDate((Date)resultSetAsList.get(i)[2]);
				
				orders.add(order);
			}
			orderLines = new OrderLines();
			orderLines.setLines_id((Integer)resultSetAsList.get(i)[3]);
			orderLines.setProduct_id((Integer)resultSetAsList.get(i)[4]);
			orderLines.setPurchase_price(Double.valueOf(resultSetAsList.get(i)[5].toString()));
			orderLines.setQuantity((Integer)resultSetAsList.get(i)[6]);
			order.getOrderLines().add(orderLines);
					
		}

		return orders;
	}
	
	public int createOrderDetails(Order order, OrderLines orderLines) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select id from orders where id = ?");
		statement.setInt(1, order.getId());
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, purchase_price, quantity) values(?,?,?,?)");
			statement2.setInt(1, orderLines.getOrder_id());
			statement2.setInt(2, orderLines.getProduct_id());
			statement2.setDouble(3, orderLines.getPurchase_price());
			statement2.setInt(4, orderLines.getQuantity());
			statement2.execute();
		} else {
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO orders (customer_id, date) values(?,?)");
			statement2.setInt(1, order.getCustomer_id());
			statement2.setDate(2, new java.sql.Date(order.getDate().getTime()));
			statement2.execute();

			PreparedStatement statement3 = connection.prepareStatement("INSERT INTO order_lines (order_id, product_id, purchase_price, quantity) values(?,?,?,?)");
			statement3.setInt(1, orderLines.getOrder_id());
			statement3.setInt(2, orderLines.getProduct_id());
			statement3.setDouble(3, orderLines.getPurchase_price());
			statement3.setInt(4, orderLines.getQuantity());
			statement3.execute();
		}
		

		return 0;
	}
}
