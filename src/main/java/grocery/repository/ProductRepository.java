package grocery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grocery.model.Product;
import grocery.model.ProductRetiree;

public class ProductRepository {
	private Connection connection;
	
	public ProductRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	public Product createProduct(Product product) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, price, in_stock) VALUES (?, ?, ?) RETURNING id");
		statement.setString(1, product.getName());
		statement.setDouble(2, product.getPrice());
		statement.setBoolean(3, product.getInStock());
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		int productId = resultSet.getInt("id");
		product.setId(productId);
		if (product instanceof ProductRetiree) {
			statement = connection.prepareStatement("INSERT INTO product_retirees (id, discount) VALUES (?, ?)");
			statement.setInt(1, productId);
			ProductRetiree y = (ProductRetiree)product;
			statement.setDouble(2, y.getDiscount());
			statement.execute();
		}
		return product;
	}
	
	public List<Product> readProducts() throws SQLException {
		List<Product> products = new ArrayList<>();
		Product product;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM products left join product_retirees on products.id = product_retirees.id;");
		while(resultSet.next()) {
			product = new Product(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"));
			product.setId(resultSet.getInt("id"));
			products.add(product);		
		};
		return products;
	}
	
	public Product readProduct(Integer productId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM products left join product_retirees on products.id = product_retirees.id where products.id = ?");
		statement.setInt(1, productId);
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		
		//de adaugat scanner de discount
		Product product = new Product(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"));
		product.setId(resultSet.getInt("id"));		
		return product;
	}
	
	public void updateProduct(Product product) throws SQLException {
	    PreparedStatement statement = connection.prepareStatement("UPDATE products set name=?, price=?, in_stock=? WHERE id=?");
	    statement.setString(1, product.getName());
	    statement.setDouble(2, product.getPrice());
	    statement.setBoolean(3, product.getInStock());
	    statement.setInt(4, product.getId());
	    statement.execute();
	        if (product instanceof ProductRetiree) {
	            statement = connection.prepareStatement("UPDATE product_retirees SET discount=? WHERE id=?");
	            statement.setDouble(1, ((ProductRetiree) product).getDiscount());
	            statement.setInt(2, product.getId());
	            statement.execute();
	        }
	    }
	
	public void deleteProduct(int productId) throws SQLException {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id=?");
	    statement.setInt(1, productId);
	    statement.execute();
	    
	    statement = connection.prepareStatement("DELETE FROM product_retirees WHERE id=?");
	    statement.setInt(1, productId);
	    statement.execute();
	}

}
