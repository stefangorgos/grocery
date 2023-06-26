package grocery.service;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import grocery.model.Product;
import grocery.repository.ProductRepository;

@ManagedBean(name = "productService")
@SessionScoped
public class ProductService {
	private ProductRepository productRepository = new ProductRepository();
	
	private List<Product> products;
	private Product selectedProduct = new Product();
	
	public void init() throws SQLException {
		products = productRepository.readProducts();
		selectedProduct = new Product();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
}
