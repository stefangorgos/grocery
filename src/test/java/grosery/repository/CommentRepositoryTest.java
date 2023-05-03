package grosery.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import grosery.model.Comment;
import grosery.model.Product;

public class CommentRepositoryTest {
	
	@Test
	public void testGetComments() throws SQLException {
		CommentRepository commentRepository = new CommentRepository();
		List<Comment> comments = commentRepository.getComments();
		assertTrue(comments.size() > 0);
		for(Comment comment : comments) {
			assertNotNull(comment.getProduct().getName());
		}
	}
	
	@Test
	public void testGetProductsWithComments() throws SQLException {
		CommentRepository commentRepository = new CommentRepository();
		List<Product> products = commentRepository.getProductsWithComments();
		assertTrue(products.size() > 0);
	}

	@Test
	public void testAddcomment() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		CommentRepository commentRepository = new CommentRepository();
		Comment comment = new Comment();
		comment.setText("Merita a fi \"Prezident\"");
		comment.setDate(new Date());
		comment.setRating(7);
		Product product = productRepository.readProduct(1);
		comment.setProduct(product);
		int rowsInserted = commentRepository.addComment(comment);
		assertEquals(1, rowsInserted);
	}

	
	@Test
	public void testPersisteComment() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		CommentRepository commentRepository = new CommentRepository();
		Comment comment = new Comment();
		comment.setText("Merita mult a fi \"Prezident\"");
		comment.setDate(new Date());
		comment.setRating(8);
		Product product = productRepository.readProduct(1);
		comment.setProduct(product);
		comment = commentRepository.persisteComment(comment);
		assertTrue(comment.getId() > 1);
	}
}
