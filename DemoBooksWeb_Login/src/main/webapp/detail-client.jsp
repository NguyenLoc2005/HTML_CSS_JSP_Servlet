<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiet Sach</title>
</head>
<body>
    <h2>${books.name}</h2>
    <p>Tac gia: ${books.author}</p>
    <p>Gia: ${books.price}</p>
    <p>Xem hinh anh sach:</p>
	<img src="${books.imagePath}" alt="${books.name}" width="150">
	<br><br>

    <a href="ClientHome">Quay lai danh sach Sach</a>
</body>
</html>
