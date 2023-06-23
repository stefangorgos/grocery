<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<%@ page import="grocery.model.Comment" %>
<%@ page import="grocery.repository.CommentRepository" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>table, th, td { border: 1px solid black;}</style>
</head>
<body>
	<%
	CommentRepository commentRepository = new CommentRepository();
	List<Comment> comments = commentRepository.getComments();
	
	%>
	<h1>Lista de comentarii</h1>
	<table>
	<tr><td>ID</td><td>Produs</td><td>Text</td><td>Rating</td><td>Data</td></tr>
	<%
	for(Comment comment : comments) { %>
		<tr><td><%=comment.getId() %></td><td><%=comment.getProduct().getName() %></td><td><%=comment.getText() %></td><td><%=comment.getRating() %></td><td><%=comment.getDate() %></td></tr>
	
	<%
	}
	%>
	
	</table>
	<p style="color: red">
	<%= comments.size() %>
	</p>
</body>
</html>