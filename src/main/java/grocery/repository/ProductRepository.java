package grocery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grocery.model.Product;
import grocery.model.ProductExpirables;
import grocery.model.ProductPerishables;
import grocery.model.ProductRetiree;

public class ProductRepository {
	private Connection connection;
	
	public ProductRepository() {
		connection = grocery.utils.DataBaseConnection.getConnnection();
	}
	public Product createProduct(Product product) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, price, in_stock, image_path) VALUES (?, ?, ?, ?) RETURNING id");
		statement.setString(1, product.getName());
		statement.setDouble(2, product.getPrice());
		statement.setBoolean(3, product.getInStock());
		statement.setString(4, product.getImagePath());
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
		Product product = null;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM products \r\n"
				+ "left join product_retirees on products.id = product_retirees.id \r\n"
				+ "left join product_expirables on products.id = product_expirables.id\r\n"
				+ "left join product_perishables on products.id = product_perishables.id\r\n"
				+ "order by products.id asc");
		while(resultSet.next()) {
			if (resultSet.getDouble("discount") != 0.0) {
				product = new ProductRetiree(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"), resultSet.getString("image_path"), resultSet.getDouble("discount"));
			} else {
				product = new Product(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"), resultSet.getString("image_path"));
			}
			if (resultSet.getDate("expiration_date") != null) {
				product = new ProductExpirables(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"), resultSet.getString("image_path"), resultSet.getDate("expiration_date"));
			}
			if (resultSet.getDate("start_date") != null) {
				product = new ProductPerishables(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"), resultSet.getString("image_path"), resultSet.getDate("expiration_date"), resultSet.getDouble("daily_discount"));
			}
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
		Product product = new Product(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getBoolean("in_stock"), resultSet.getString("image_path"));
		product.setId(resultSet.getInt("id"));		
		return product;
	}
	
	public void updateProduct(Product product) throws SQLException {
	    PreparedStatement statement = connection.prepareStatement("UPDATE products set name=?, price=?, in_stock=?, image_path=? WHERE id=?");
	    statement.setString(1, product.getName());
	    statement.setDouble(2, product.getPrice());
	    statement.setBoolean(3, product.getInStock());
	    statement.setString(4, product.getImagePath());
	    statement.setInt(5, product.getId());
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
