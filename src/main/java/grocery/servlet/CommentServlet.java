package grocery.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grocery.model.Comment;
import grocery.repository.CommentRepository;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = -5125265994866891818L;

	public CommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		CommentRepository commentRepository = new CommentRepository();
		List<Comment> comments;
		try {
			comments = commentRepository.getComments();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		writer.print("<style>table, th, td { border: 1px solid black;}</style>");		
		writer.print("<html><body>"); 
		writer.print("<h1>Lista comentariilor</h1>");
		writer.print("<table>");
		writer.print("<tr><td>ID</td><td>Produs</td><td>Text</td><td>Rating</td><td>Data</td></tr>");
		for(Comment comment : comments) {
			writer.print("<tr>");
			writer.print("<td>" + comment.getId() + "</td>");
			writer.print("<td>" + comment.getProduct().getName() + "</td>");
			writer.print("<td>" + comment.getText() + "</td>");
			writer.print("<td>" + comment.getRating() + "</td>");
			writer.print("<td>" + comment.getDate() + "</td>");
			writer.print("</tr>");

		}
		
		writer.print("</table>");
		writer.print("</body></html>"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentRepository commentRepository = new CommentRepository();
		Comment comment = new Comment();
		comment.setProductId(Integer.valueOf(request.getParameter("product_id")));
		comment.setText(request.getParameter("text"));
		comment.setDate(Date.valueOf(request.getParameter("date")));
		comment.setRating(Double.valueOf(request.getParameter("rating")));
		try {
			commentRepository.addComment(comment);
		} catch (SQLException e) {
			throw new IOException(e);
		}
		request.getRequestDispatcher("comments.jsf").forward(request, response);
	}

}
