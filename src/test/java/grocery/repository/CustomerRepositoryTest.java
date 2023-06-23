package grocery.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	@Test
	public void testAddCustomer() throws SQLException {
		CustomerRepository customerRepository = new CustomerRepository();
		Customer customer = new Customer();
		customer.setName("USM");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse("2023-05-01");
			System.out.println(date);
		} catch (ParseException e) {
			// do nothing by design
		}
		customer.setCustomerSince(date);
		int rowsInserted = customerRepository.addCustomer(customer);
		assertEquals(1, rowsInserted);
	}
	
	@Test
	public void testUpdateCustomer() throws SQLException {
		CustomerRepository customerRepository = new CustomerRepository();
		Customer customer = new Customer();
		customer.setName("Fidesco");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse("2021-10-01");
		} catch (ParseException e) {
			// do nothing by design
		}
		customer.setCustomerSince(date);
		customer.setId(7);
		customerRepository.updateCustomer(customer);
	}
	
	@Test
	public void testDeleteCustomer() throws SQLException {
		CustomerRepository customerRepository = new CustomerRepository();
		customerRepository.deleteCustomer(7);
	}

}
