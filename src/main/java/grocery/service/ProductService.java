package grocery.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import grocery.model.Product;
import grocery.repository.ProductRepository;

@ManagedBean(name = "productService")
@SessionScoped
public class ProductService {
	private ProductRepository productRepository = new ProductRepository();
	
	private List<Product> products;
	private Product selectedProduct = new Product();
	
	private Part imageFile;
	
	public Part getImageFile() {
		return imageFile;
	}
	
	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}
	
	public void init() throws SQLException {
		if (selectedProduct.getId() == null) {
			selectedProduct = new Product();
		} else {
			selectedProduct = productRepository.readProduct(selectedProduct.getId());
		}
		products = productRepository.readProducts();
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public List<Product> getAll() {
		try {
			return productRepository.readProducts();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
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
	
	public String saveProduct() throws IOException {
		String fileName = String.valueOf(Paths.get(imageFile.getSubmittedFileName()));
		File savedFile = new File("C:\\git\\grocery\\src\\main\\webapp\\images", fileName);
		
		try (InputStream input = imageFile.getInputStream()) {
			Files.copy(input, savedFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String imagePath = String.valueOf(savedFile.getName());
		selectedProduct.setImagePath(imagePath);
		
		try {
			productRepository.createProduct(selectedProduct);
			return "products";
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	public String updateProduct() throws IOException {
			
		if(imageFile == null) {
			selectedProduct.setImagePath(selectedProduct.getImagePath());
		} else {
			String fileName = String.valueOf(Paths.get(imageFile.getSubmittedFileName()));
			File savedFile = new File("C:\\git\\grocery\\src\\main\\webapp\\images", fileName);
			
			try (InputStream input = imageFile.getInputStream()) {
				Files.copy(input, savedFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String imagePath = String.valueOf(savedFile.getName());
			selectedProduct.setImagePath(imagePath);
		}
				
		try {
			productRepository.updateProduct(selectedProduct);
			return "products";
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
