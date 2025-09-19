<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu-Client</title>
</head>
<body>
	<h2>Danh sach Sach</h2>
	<table border=1 cellpadding=10>
		<tr>
			<th>Ma sach</th>
			<th>Ten sach</th>
			<th>Tac gia</th>
			<th>Hanh dong</th>
		</tr>
		<c:forEach var="books" items="${booksList }">
			<tr>
			<td>${books.id }</td>
			<td>${books.name }</td>
			<td>${books.author }</td>
			<td>
				<a href="ClientHome?action=detail&id=${books.id }">Xem chi tiet</a>
			</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>