package grocery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grocery.dto.CustomerDTO;
import grocery.model.Customer;

public class CustomerRepository implements IRepository<Customer>{
	private Connection connection;
	
	public CustomerRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	

	public int addCustomer(Customer customer) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (name, customer_since) VALUES (?, ?)");
		statement.setString(1, customer.getName());
		statement.setDate(2, new java.sql.Date(customer.getCustomerSince().getTime()));
		return statement.executeUpdate();
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
	
	public void updateCustomer(Customer customer) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("UPDATE customers set name = ?, customer_since = ? WHERE id = ?");
		statement.setString(1, customer.getName());
		statement.setDate(2, new java.sql.Date(customer.getCustomerSince().getTime()));
		statement.setInt(3, customer.getId());
		statement.executeUpdate();
	}
	
	public void deleteCustomer(int customerId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE id=?");
	    statement.setInt(1, customerId);
	    statement.execute();
	}

	public Customer readCustomer(int customerId) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE id >= " + customerId);
	    resultSet.next();
		Customer customer = new Customer();
		customer.setId(resultSet.getInt("id"));
		customer.setName(resultSet.getString("name"));
		customer.setCustomerSince(resultSet.getDate("customer_since"));
		return customer;
	}


	@Override
	public List<Customer> getEntities() {
		try {
			return readCustomers();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public Customer getEntityById(Integer id) {
		try {
			return readCustomer(id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
