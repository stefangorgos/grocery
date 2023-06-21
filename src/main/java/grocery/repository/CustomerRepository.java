package grocery.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grocery.dto.CustomerDTO;
import grocery.model.Customer;

public class CustomerRepository {
	private Connection connection;
	
	public CustomerRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	
	public List<Customer> readCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		Customer customer;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from customers");
		while(resultSet.next()) {
			customer = new Customer();
			customer.setId(resultSet.getInt("id"));
			customer.setName(resultSet.getString("name"));
			customer.setCustomerSince(resultSet.getDate("customer_since"));
			customers.add(customer);
		}
		
		
		return customers;
	}
	
	public List<CustomerDTO> readCustomersWithDetails() throws SQLException {
		List<CustomerDTO> customers = new ArrayList<>();
		CustomerDTO customer;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select customers.id, customers.name, customer_since, \r\n"
				+ "	count(orders.id) as order_count , sum(order_lines.quantity*order_lines.purchase_price) as total_spent \r\n"
				+ "from customers \r\n"
				+ "join orders on orders.customer_id = customers.id \r\n"
				+ "join order_lines on order_lines.order_id = orders.id\r\n"
				+ "group by customers.id");
		while(resultSet.next()) {
			customer = new CustomerDTO();
			customer.setId(resultSet.getInt("id"));
			customer.setName(resultSet.getString("name"));
			customer.setCustomerSince(resultSet.getDate("customer_since"));
			customer.setOrderCount(resultSet.getInt("order_count"));
			customer.setTotalSpent(resultSet.getDouble("total_spent"));
			customers.add(customer);
		}
		
		
		return customers;
	}
	
	

}
