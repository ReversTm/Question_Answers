<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Books</title>
</head>
<body>
<h1>List of Books</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Genre</th>
        <th>Author</th>
    </tr>
    <c:forEach var="book" items="${allBooks}">
        <tr>
            <td>${book.name}</td>
            <td>${book.genre}</td>
            <td>${book.author}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>