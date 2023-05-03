package grosery.utils;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConnection {
	static final String DB_URL = "jdbc:postgresql://localhost:5432/grosery";
	static final String USER = "postgres";
	static final String PASS = "postgres";
	
	public static Connection getConnnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		try (
			Connection conn = getConnnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from comments");) {
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getInt("id"));
				System.out.print(", Product: " + rs.getInt("product_id"));
				System.out.print(", Text: " + rs.getString("text"));
				System.out.print(", Date: " + rs.getDate("date"));
				System.out.println(", Rating: " + rs.getFloat("rating"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
