package grocery.repository;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import grocery.dto.CustomerDTO;
import grocery.model.Customer;

public class CustomerRepositoryTest {
	
	@Test
	public void testReadCustomers() throws SQLException {
		CustomerRepository customerRepository = new CustomerRepository();
		List<Customer> customers = customerRepository.readCustomers();
		assertTrue(customers.size() > 0);
	}
	
	@Test
	public void testReadCustomersWithDetails() throws SQLException {
		CustomerRepository customerRepository = new CustomerRepository();
		List<CustomerDTO> customers = customerRepository.readCustomersWithDetails();
		assertTrue(customers.size() > 0);
	}

}
