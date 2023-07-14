package grocery.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import grocery.repository.CommentRepository;

@WebServlet("/rest/comments")
public class CommentREST extends HttpServlet {
	private static final long serialVersionUID = 5799662821047832854L;
	private Gson gson = new Gson();
	private CommentRepository commentRepository = new CommentRepository(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respon = new String();
		try {
			respon = gson.toJson(commentRepository.getComments());
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(respon);
        out.flush();   
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    BufferedReader reader = request.getReader();
	    char[] content = new char[request.getContentLength()];
	    reader.read(content);
	    String data = new String(content);
	    System.out.println(data);
	    
		String respon = new String("{\"response\": \"OK!\"}");
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(respon);
        out.flush();   
	}
}
