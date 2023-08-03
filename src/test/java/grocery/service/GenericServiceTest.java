package grocery.service;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import grocery.model.Comment;
import grocery.model.Customer;
import grocery.repository.CommentRepository;
import grocery.repository.CustomerRepository;

public class GenericServiceTest {

	@Test
	public void testGetComments() throws SQLException {
		GenericService<CommentRepository, Comment> commentRepository = new GenericService<CommentRepository, Comment>();
		commentRepository.setRepository(new CommentRepository());
		List<Comment> comments = commentRepository.getEntities();
		assertTrue(comments.size() > 0);
		for(Comment comment : comments) {
			System.out.print(comment.getId());
			System.out.print(comment.getProduct().getName());
			System.out.print(comment.getText());
			System.out.print(comment.getRating());
			System.out.println(comment.getDate());

		}
	}
	
	@Test
	public void testGetCommentById() throws SQLException {
		GenericService<CommentRepository, Comment> commentRepository = new GenericService<CommentRepository, Comment>();
		commentRepository.setRepository(new CommentRepository());
		
		Comment comment = commentRepository.getEntityBy(1);
		System.out.print(comment.getId());
		System.out.print(comment.getText());
		System.out.print(comment.getRating());
		System.out.println(comment.getDate());

	}
	
	@Test
	public void testGetCustomers() throws SQLException {
		GenericService<CustomerRepository, Customer> customerRepository = new GenericService<CustomerRepository, Customer>();
		customerRepository.setRepository(new CustomerRepository());
		
		List<Customer> customers = customerRepository.getEntities();
		assertTrue(customers.size() > 0);
	}
	
	@Test
	public void testGetCustomerById() throws SQLException {
		GenericService<CustomerRepository, Customer> customerRepository = new GenericService<CustomerRepository, Customer>();
		customerRepository.setRepository(new CustomerRepository());
		
		Customer cusomer = customerRepository.getEntityBy(1);
		System.out.print(cusomer.getId());
		System.out.print(cusomer.getName());
		System.out.print(cusomer.getCustomerSince());
	}
	
}
