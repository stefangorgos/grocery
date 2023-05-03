package grosery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grosery.model.Comment;
import grosery.model.Product;
import grosery.utils.DataBaseConnection;

public class CommentRepository {
	private Connection connection;
	
	public CommentRepository() {
		connection = DataBaseConnection.getConnnection();
	}
	
	public List<Comment> getComments() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select comments.id as comment_id, product_id, text, date, rating, name, price, in_stock from comments join products on comments.product_id = products.id"); 
		List<Comment> comments = new ArrayList<>();
		Comment comment;
		Product product;
		while (resultSet.next()) {
			comment = new Comment();
			comment.setId(resultSet.getInt("comment_id"));
			comment.setText(resultSet.getString("text"));
			comment.setDate(resultSet.getDate("date"));
			comment.setRating(resultSet.getFloat("rating"));
			
			product = new Product();
			product.setId(resultSet.getInt("product_id"));
			product.setName(resultSet.getString("name"));
			product.setPrice(resultSet.getDouble("price"));
			product.setInStock(resultSet.getBoolean("in_stock"));
			comment.setProduct(product);
			
			comments.add(comment);
		}
		return comments;
	}
	
	public List<Product> getProductsWithComments() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select products.id, name, price, in_stock, comments.id as comment_id, text, date, rating from products join comments on products.id = comments.product_id order by products.id asc"); 
		List<Product> products = new ArrayList<>();
		Comment comment;
		Product product = null;
		Integer previousProductId = -1;
		while (resultSet.next()) {
			final Integer currentProductId = resultSet.getInt("id");
			if (!previousProductId.equals(currentProductId)) {
				previousProductId = currentProductId;
				product = new Product();
				product.setComments(new ArrayList<Comment>());
				product.setId(currentProductId);
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				product.setInStock(resultSet.getBoolean("in_stock"));	
				
				products.add(product);
			}
			
			comment = new Comment();
			comment.setId(resultSet.getInt("comment_id"));
			comment.setText(resultSet.getString("text"));
			comment.setDate(resultSet.getDate("date"));
			comment.setRating(resultSet.getFloat("rating"));
			product.getComments().add(comment);			
		}
		return products;
	}

	public int addComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (product_id, text, date, rating) VALUES (?, ?, ?, ?)");
		statement.setInt(1, comment.getProduct().getId());
		statement.setString(2, comment.getText());
		statement.setDate(3, new java.sql.Date(comment.getDate().getTime()));
		statement.setDouble(4, comment.getRating().doubleValue());
		return statement.executeUpdate();
	}

	public Comment persisteComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (product_id, text, date, rating) VALUES (?, ?, ?, ?) RETURNING id");
		statement.setInt(1, comment.getProduct().getId());
		statement.setString(2, comment.getText());
		statement.setDate(3, new java.sql.Date(comment.getDate().getTime()));
		statement.setDouble(4, comment.getRating().doubleValue());
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		comment.setId(resultSet.getInt("id"));
		return comment;
	}
}
