<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Answer Information</title>
</head>
<body>
<h1>Question and Answers</h1>

<c:choose>
    <c:when test="${not empty question}">
        <p>Author: ${question.client.name}</p> <!-- Имя автора вопроса -->
        <p>Question: ${question.questionText}</p> <!-- Текст вопроса -->
    </c:when>
    <c:otherwise>
        <p>No question found.</p>
    </c:otherwise>
</c:choose>

<hr> <!-- Горизонтальная линия для разделения вопроса и ответов -->

<c:forEach var="answer" items="${answers}">
    <p><b>${answer.client.name}</b> <!-- Имя автора ответа -->: ${answer.answerText}</p> <!-- Текст ответа -->
</c:forEach>


<form action="/submit-answer" method="post">
    <input type="hidden" name="questionId" value="${question.id}" />
    Client Id: ${client.id}
    <input type="hidden" name="clientId" value="${client.id}" /> <!-- Используйте ${client.id} -->
    <textarea name="answerText" rows="4" cols="50" placeholder="Your Answer"></textarea>
    <input type="submit" value="Submit Answer" />
</form>
</body>
</html>