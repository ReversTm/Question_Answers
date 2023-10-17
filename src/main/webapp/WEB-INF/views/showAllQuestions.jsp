<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Questions</title>
</head>
<body>
<h1>List of Questions</h1>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Question</th>
        <th>Action</th> <!-- Добавлен столбец для кнопки "Ответить" -->
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry" items="${userQuestionsMap}">
        <c:forEach var="question" items="${entry.value}">
            <tr>
                <td>${entry.key.name}</td>
                <td>${entry.key.surname}</td>
                <td>${entry.key.age}</td>
                <td>${question.questionText}</td>
                <td>
                    <form action="/answer" method="get">
                        <input type="hidden" name="clientId" value="${entry.key.id}" />
                        <input type="hidden" name="questionId" value="${question.id}" />
                        <input type="submit" value="Answer" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
