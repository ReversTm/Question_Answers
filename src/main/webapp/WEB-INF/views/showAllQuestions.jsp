<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Questions</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 1000px; /* Уменьшено значение максимальной ширины */
        }
        h1 {
            background-color: #007bff;
            color: #fff;
            padding: 20px;
            text-align: center;
            border-radius: 10px 10px 0 0;
            margin: 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f0f0f0;
        }
        tr:nth-child(odd) {
            background-color: #e6e6e6;
        }
        .action-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .action-button:hover {
            background-color: #0056b3;
        }
        .back-button {
            background-color: #dc3545;
        }
        .back-button:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>List of Questions</h1>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Age</th>
            <th>Question</th>
            <th>Rating</th> <!-- Add the Rating column -->
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
                    <td>${question.rating}</td> <!-- Display the rating value -->
                    <td>
                        <form action="/answer" method="get">
                            <input type="hidden" name="clientId" value="${entry.key.id}" />
                            <input type="hidden" name="questionId" value="${question.id}" />
                            <button class="action-button" type="submit">Answer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
    <button class="action-button back-button" onclick="window.location.href = '/'">Back to Main Page</button>
</div>
</body>
</html>
