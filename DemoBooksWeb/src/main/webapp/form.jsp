<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    boolean isEdit = request.getAttribute("books") != null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= isEdit ? "Sua sach" : "Them sach" %></title>
</head>
<body>
    <h2><%= isEdit ? "Sua sach" : "Them sach" %></h2>

    <form action="books" method="POST">
        <c:if test="${books != null}">
            <input type="hidden" name="id" value="${books.id}">
        </c:if>

        Ten sach:<br>
        <input type="text" name="name" value="${books.name}" required><br><br>
        Tac gia:<br>
        <input type="text" name="author" value="${books.author}" required><br><br>
        Gia:<br>
        <input type="text" name="price" value="${books.price}" required><br><br>
        Xem hinh anh:<br>
        <input type="text" name="imagePath" value="${books.imagePath}" required><br><br>

        <input type="submit" value="<%= isEdit ? "Cap nhat" : "Tao moi" %>">
    </form>

    <a href="books">Quay lai danh sach Sach</a>
</body>
</html>
