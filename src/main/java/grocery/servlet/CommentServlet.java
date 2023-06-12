package grocery.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = -5125265994866891818L;

	public CommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print("<html><body>"); 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		writer.print("<br/><b>Hello Servlet</b><br/>"); 
		writer.printf("%s%s%s", "<div style=\"color: #ec113d\">Served from: ", request.getContextPath(), "</div>");
		writer.print("Served at: " + (new Date().toInstant().toString()));
		writer.print("<br/><b>Print LN !!!</b>");
		writer.print("</body></html>"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
