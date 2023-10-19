<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Questions</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
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
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry" items="${userQuestionsMap}">
        <c:forEach var="question" items="${entry.value}" varStatus="loop">
            <tr>
                <c:if test="${loop.index eq 0}">
                    <td rowspan="${fn:length(entry.value)}">${entry.key.name}</td>
                    <td rowspan="${fn:length(entry.value)}">${entry.key.surname}</td>
                    <td rowspan="${fn:length(entry.value)}">${entry.key.age}</td>
                </c:if>
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
