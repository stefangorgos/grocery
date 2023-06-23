<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
<jsp:useBean id="commentRepository" class="grocery.repository.CommentRepository"></jsp:useBean>
<jsp:useBean id="productRepository" class="grocery.repository.ProductRepository"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>table, th, td { border: 1px solid black;}</style>
</head>
<body>
	<c:out value="${date}"></c:out><br>
	<c:out value="${commentRepository.getComments().size()}"></c:out>
	<table>
	<tr><td>ID</td><td>Produs</td><td>Text</td><td>Rating</td><td>Data</td></tr>
	<c:forEach items="${commentRepository.getComments()}" var="comment">
		<tr>
		<td><c:out value="${comment.getId()}"></c:out></td>
		<td><c:out value="${comment.getProduct().getName()}"></c:out></td>
		<td><c:out value="${comment.getText()}"></c:out></td>
		<td><c:out value="${comment.getRating()}"></c:out></td>
		<td><c:out value="${comment.getDate()}"></c:out></td>
		</tr>
	</c:forEach>
	</table>
	<br><br>
	<table>
	<tr><td>ID</td><td>Nume</td><td>Pret</td><td>In stock</td><td>Discount</td></tr>
	<c:forEach items="${productRepository.readProducts()}" var="product">
		<tr>
		<td><c:out value="${product.id}"></c:out></td>
		<td><c:out value="${product.name}"></c:out></td>
		<td><c:out value="${product.price}"></c:out></td>
		<td><c:out value="${product.inStock}"></c:out></td>
		<c:choose>
		<c:when test="${product.getClass().getSimpleName() == 'ProductRetiree'}">
		<td><c:out value="${product.getDiscount()}"></c:out></td>
		</c:when>
		<c:otherwise>
		<td>-</td>
		</c:otherwise>
		</c:choose>


		</tr>
	</c:forEach>
	</table>
</body>
</html>