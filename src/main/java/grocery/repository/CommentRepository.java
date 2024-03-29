package grocery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import grocery.model.Comment;
import grocery.model.Product;
import grocery.utils.DataBaseConnection;

public class CommentRepository {
	private Connection connection;
	private static final Logger logger = LoggerFactory.getLogger(CommentRepository.class);
	
	public CommentRepository() {
		logger.debug("Start CommentRepository");
		connection = DataBaseConnection.getConnnection();
	}
	
	public List<Comment> getComments() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select comments.id as comment_id, product_id, text, date, rating, name, price, in_stock from comments join products on comments.product_id = products.id order by comments.id asc"); 
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
	
	public Comment getCommentById(int commentId) throws SQLException{
		Comment comment = new Comment();
		PreparedStatement statement = connection.prepareStatement("select * from comments where id = ?"); 
		statement.setInt(1, commentId);
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		comment.setId(resultSet.getInt("id"));
		comment.setProductId(resultSet.getInt("product_id"));;
		comment.setText(resultSet.getString("text"));
		comment.setRating(resultSet.getDouble("rating"));
		comment.setDate(resultSet.getDate("date"));
		return comment;
	}
	
	public List<Comment> getCommentsByProduct(int productId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select comments.id as comment_id, product_id, text, date, rating, name, price, in_stock \r\n"
				+ "from comments \r\n"
				+ "join products on comments.product_id = products.id \r\n"
				+ "where product_id = ?"); 
		statement.setInt(1, productId);
		ResultSet resultSet = statement.getResultSet();
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
		statement.setInt(1, comment.getProductId());
		statement.setString(2, comment.getText());
		statement.setDate(3, new java.sql.Date(comment.getDate().getTime()));
		statement.setDouble(4, comment.getRating().doubleValue());
		return statement.executeUpdate();
	}

	public Comment persisteComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (product_id, text, date, rating) VALUES (?, ?, ?, ?) RETURNING id");
		statement.setInt(1, comment.getProductId());
		statement.setString(2, comment.getText());
		statement.setDate(3, new java.sql.Date(comment.getDate().getTime()));
		statement.setDouble(4, comment.getRating().doubleValue());
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		comment.setId(resultSet.getInt("id"));
		return comment;
	}
	
	public void deleteCommentsByProductId(int productId) throws SQLException {
	    PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE product_id=?");
	    statement.setInt(1, productId);
	    statement.execute();
	}
	
	public void deleteCommentById(int commentId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE id = ?");
		statement.setInt(1, commentId);
		statement.execute();
	}
	
	public int updateComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("UPDATE comments set product_id = ?, text = ?, date = ?, rating = ? where id = ?");
		statement.setInt(1, comment.getProductId());
		statement.setString(2, comment.getText());
		statement.setDate(3, new java.sql.Date(comment.getDate().getTime()));
		statement.setDouble(4, comment.getRating().doubleValue());
		statement.setInt(5, comment.getId());
		return statement.executeUpdate();
	} 
}
