<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Answer Information</title>
    <style>
        .answer {
            display: flex;
            align-items: center;
        }
        .vote-form {
            margin-left: 10px;
        }
        .rating {
            margin-left: 10px;
        }
    </style>
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
    <div class="answer">
        <p><b>${answer.user.name}</b>: ${answer.answerText}</p> <!-- Текст ответа -->
        <form action="/submit-vote" method="post" class="vote-form">
            <input type="hidden" name="answerId" value="${answer.id}" />
            <input type="hidden" name="voteValue" value="1" />
            <input type="submit" value="+" />
        </form>
        <span id="rating-${answer.id}" class="rating">${answer.rating}</span> <!-- Счетчик рейтинга -->
        <form action="/submit-vote" method="post" class="vote-form">
            <input type="hidden" name="answerId" value="${answer.id}" />
            <input type="hidden" name="voteValue" value="-1" />
            <input type="submit" value="-" />
        </form>
    </div>
</c:forEach>

<form action="/submit-answer" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <%--является частью защиты от CSRF (межсайтовой подделки запросов) и представляет собой скрытое поле, которое содержит токен CSRF (кросс-сайт-запрос).--%>
    <input type="hidden" name="questionId" value="${question.id}" />
<%--    <input type="hidden" name="clientId" value="${user.id}" />--%>
    <textarea name="answerText" rows="4" cols="50" placeholder="Your Answer"></textarea>
    <input type="submit" value="Submit Answer" />
</form>
<a href="/showAllQuestions">Back to all Questions</a>
</body>
</html>
