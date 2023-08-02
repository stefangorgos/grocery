package grocery.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import grocery.model.Product;
import grocery.model.ProductRetiree;

public class ProductRepositoryTest {
	
	@Test
	public void testCreateProduct() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product product = new Product("Lapte", 23.75, false, null);
		Product productSaved = productRepository.createProduct(product);
		assertTrue(productSaved.getId() > 1);
	}
	
	@Test
	public void testCreateProductRetiree() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product product = new ProductRetiree("Peste", 25.50, false, null, 0.2);
		Product productSaved = productRepository.createProduct(product);
		assertTrue(productSaved.getId() > 1);
	}
	
	@Test
	public void testReadProducts() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		List<Product> products = productRepository.readProducts();
		assertTrue(products.size() > 0);
	}
	
	@Test
	public void testReadProduct() throws SQLException {
		ProductRepository productRepository = new ProductRepository();
		Product product = productRepository.readProduct(8);
		assertEquals(Integer.valueOf(8), product.getId());
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
		Product product = new Product("To be deleted", 23.75, false, null);
		Product productSaved = productRepository.createProduct(product);
		int productId = productSaved.getId();
		productRepository.deleteProduct(productId);
	}
}
