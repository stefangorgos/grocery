package grosery.repository;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import grosery.model.Product;
import grosery.model.ProductRetiree;

public class ProductRepositoryTest {
	
//	@Test
//	public void testGetComments() throws SQLException {
//		CommentRepository commentRepository = new CommentRepository();
//		List<Comment> comments = commentRepository.getComments();
//		assertTrue(comments.size() > 0);
//		for(Comment comment : comments) {
//			assertNotNull(comment.getProduct().getName());
//		}
//	}
//	
//	@Test
//	public void testGetProductsWithComments() throws SQLException {
//		CommentRepository commentRepository = new CommentRepository();
//		List<Product> products = commentRepository.getProductsWithComments();
//		assertTrue(products.size() > 0);
//	}

	@Test
	public void testCreateProduct() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product product = new Product("Lapte", 23.75, false);
		Product productSaved = productRepository.createProduct(product);
		assertTrue(productSaved.getId() > 1);
	}
	
	@Test
	public void testCreateProductRetiree() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product product = new ProductRetiree("Peste", 25.50, false, 0.2);
		Product productSaved = productRepository.createProduct(product);
		assertTrue(productSaved.getId() > 1);
	}
	
	@Test
	public void testReadProducts() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		List<Product> products = productRepository.readProducts();
		System.out.println(products);
	}
	
	@Test
	public void testReadProduct() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product product = productRepository.readProduct(8);
		System.out.println(product);
	}
	
	@Test
	public void testUpdateProductPrice() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product peste = productRepository.readProduct(15);
		peste.setPrice(22.20);
		productRepository.updateProduct(peste);		
	}
	
	@Test
	public void testUpdateProductDiscount() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product peste = productRepository.readProduct(15);
		peste.setPrice(22.20);
		productRepository.updateProduct(peste);		
	}

	@Test
	public void testDeleteProduct() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		productRepository.deleteProduct(1);
	}
	
//	@Test
//	public void testPersisteComment() throws SQLException {
//		CommentRepository commentRepository = new CommentRepository();
//		Comment comment = new Comment();
//		comment.setText("Merita mult a fi \"Prezident\"");
//		comment.setDate(new Date());
//		comment.setRating(8);
//		comment = commentRepository.persisteComment(comment);
//		assertTrue(comment.getId() > 1);
//	}
}
