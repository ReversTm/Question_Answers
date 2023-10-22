<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Answer Information</title>
</head>
<body>
<h1>Question and Answers</h1>

<c:choose>
    <c:when test="${not empty question}">
        <p>Author: ${question.user.name}</p> <!-- Имя автора вопроса -->
        <p>Question: ${question.questionText}</p> <!-- Текст вопроса -->
    </c:when>
    <c:otherwise>
        <p>No question found.</p>
    </c:otherwise>
</c:choose>

<hr> <!-- Горизонтальная линия для разделения вопроса и ответов -->

<c:forEach var="answer" items="${answers}">
    <p><b>${answer.user.name}</b> <!-- Имя автора ответа -->: ${answer.answerText}</p> <!-- Текст ответа -->
</c:forEach>


<form action="/submit-answer" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="questionId" value="${question.id}" />
    <input type="hidden" name="clientId" value="${user.id}" />
    <textarea name="answerText" rows="4" cols="50" placeholder="Your Answer"></textarea>
    <input type="submit" value="Submit Answer" />
</form>
<a href="/showAllQuestions">Back to all Questions</a>
</body>
</html>