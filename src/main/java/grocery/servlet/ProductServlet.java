package grocery.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grocery.model.Product;
import grocery.model.ProductExpirables;
import grocery.model.ProductPerishables;
import grocery.model.ProductRetiree;
import grocery.repository.ProductRepository;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = -5125265994866891818L;
	
	public ProductServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		ProductRepository productRepository = new ProductRepository();
		List<Product> products;
		try {
			products = productRepository.readProducts();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		writer.print("<style>table, th, td { border: 1px solid black;}</style>");		
		writer.print("<html><body>"); 
		writer.print("<h1>Lista produselor</h1>");
		writer.print("<table>");
		writer.print("<tr><td>ID</td><td>Produs</td><td>Pret</td><td>In stock</td><td>Discount pensionari</td><td>Data expirarii</td><td>Discount zilnic</td></tr>");
		for(Product product : products) {
			writer.print("<tr>");
			writer.print("<td>" + product.getId() + "</td>");
			writer.print("<td>" + product.getName() + "</td>");
			writer.print("<td>" + product.getPrice() + "</td>");
			writer.print("<td>" + product.getInStock() + "</td>");
			if (product instanceof ProductRetiree) {
				writer.print("<td>" + ((ProductRetiree) product).getDiscount() + "</td>");
			} else {
				writer.print("<td>-</td>");
			}
			if (product instanceof ProductExpirables) {
				writer.print("<td>" + ((ProductExpirables) product).getExpirationDate() + "</td>");
			} else {
				writer.print("<td>-</td>");
			}
			if (product instanceof ProductPerishables) {
				writer.print("<td>" + ((ProductPerishables) product).getDailyDiscount() + "</td>");
			} else {
				writer.print("<td>-</td>");
			}

			writer.print("</tr>");

		}
		
		writer.print("</table>");
		writer.print("</body></html>"); 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductRepository productRepository = new ProductRepository();
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(Double.valueOf(request.getParameter("price")));
		product.setInStock(Boolean.valueOf(request.getParameter("instock")));
		product.setImagePath(String.valueOf(request.getParameter("image_path")));
		if (request.getParameter("discount") != null) {
			product = new ProductRetiree(request.getParameter("name"), Double.valueOf(request.getParameter("price")), Boolean.valueOf(request.getParameter("instock" )), String.valueOf(request.getParameter("image_path")), Double.valueOf(request.getParameter("discount")));
		}
		try {
			productRepository.createProduct(product);
		} catch (SQLException e) {
			throw new IOException(e);
		}
		doGet(request, response);
	}
	
}
