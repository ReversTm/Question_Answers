<%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
    <c:forEach var="client" items="${allClients}">
        <tr>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.age}</td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach var="question" items="${userQuestionsMap[client]}">

            <%--<c:url var="answer" value="/answer">
                <c:param name="questionId" value="${question.id}"/>
            </c:url>--%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>${question.questionText}</td>
                <td>
                    <form action="/answer" method="get">
                        <input type="hidden" name="clientId" value="${client.id}" />
                        <input type="hidden" name="questionId" value="${question.id}" />
                        <input type="submit" value="Answer" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>
        <%-- <c:forEach var="question" items="${allQuestions}">

              <td>${question.questionText}</td>

          </c:forEach>--%>

            <%--<c:forEach var="entry" items="${userQuestionsMap}">
                <h2>User: ${entry.key.name} ${entry.key.surname}</h2>
                <ul>
                    <c:forEach var="question" items="${entry.value}">
                        <li>${question.questionText}</li>
                    </c:forEach>
                </ul>
            </c:forEach>
            <td>
                <input type="button" value="Answer"
                       onclick="window.location.href = '${answer}'"/>
            </td>--%>

</body>
</html>