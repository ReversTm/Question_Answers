<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
        .edit-button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            padding: 5px 10px;
        }
        .edit-modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .edit-form {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .edit-form input[type="submit"], .edit-form button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            margin: 10px 0;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Question and Answers</h1>

<c:choose>
    <c:when test="${not empty question}">
        <p>Author: ${question.user.name}</p> <!-- Имя автора вопроса -->
        <p>Question: ${question.questionText}</p> <!-- Текст вопроса -->

        <form action="/submit-vote-for-question" method="post" class="vote-form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="questionId" value="${question.id}" />
            <input type="hidden" name="voteValue" value="1" />
            <input type="submit" value="+" />
        </form>
        <span id="question-rating" class="rating">${question.rating}</span> <!-- Счетчик рейтинга -->
        <form action="/submit-vote-for-question" method="post" class="vote-form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="questionId" value="${question.id}" />
            <input type="hidden" name="voteValue" value="-1" />
            <input type="submit" value="-" />
        </form>
    </c:when>
    <c:otherwise>
        <p>No question found.</p>
    </c:otherwise>
</c:choose>

<hr> <!-- Горизонтальная линия для разделения вопроса и ответов -->

<c:forEach var="answer" items="${answers}">
    <div class="answer">
        <p><b>${answer.user.name}</b>: ${answer.answerText}</p> <!-- Текст ответа -->
        <c:if test="${answer.user.name == currentUserName}">
            <button class="edit-button" onclick="openEditForm(${answer.id})">Edit</button>
            <button class="edit-button" onclick="openDeleteConfirmation(${answer.id})">Delete</button>

            <div id="editModal-${answer.id}" class="edit-modal">
                <div class="edit-form">
                    <form action="/edit-answer" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" name="questionId" value="${question.id}" />
                        <input type="hidden" name="answerId" value="${answer.id}" />
                        <textarea name="answerText" rows="4" cols="50" placeholder="Your Answer">${answer.answerText}</textarea>
                        <input type="submit" value="Confirm Edit" />
                    </form>
                    <button onclick="closeEditForm(${answer.id})">Close</button>
                </div>
            </div>

            <!-- Delete confirmation modal -->
            <div id="deleteConfirmation-${answer.id}" class="edit-modal">
                <div class="edit-form">
                    <h2>Are you sure you want to delete this answer?</h2>
                    <form action="/delete-answer" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" name="questionId" value="${question.id}" />
                        <input type="hidden" name="answerId" value="${answer.id}" />
                        <input type="submit" value="Confirm Delete" />
                    </form>
                    <button onclick="closeDeleteConfirmation(${answer.id})">Close</button>
                </div>
            </div>
        </c:if>

        <form action="/submit-vote-for-answer" method="post" class="vote-form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="questionId" value="${question.id}" />
            <input type="hidden" name="answerId" value="${answer.id}" />
            <input type="hidden" name="voteValue" value="1" />
            <input type="submit" value="+" />
        </form>
        <span id="rating-${answer.id}" class="rating">${answer.rating}</span> <!-- Счетчик рейтинга -->
        <form action="/submit-vote-for-answer" method="post" class="vote-form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="questionId" value="${question.id}" />
            <input type="hidden" name="answerId" value="${answer.id}" />
            <input type="hidden" name="voteValue" value="-1" />
            <input type="submit" value="-" />
        </form>
    </div>
</c:forEach>

<script>
    function openEditForm(answerId) {
        document.getElementById('editModal-' + answerId).style.display = "block";
    }

    function closeEditForm(answerId) {
        document.getElementById('editModal-' + answerId).style.display = "none";
    }

    function openDeleteConfirmation(answerId) {
        document.getElementById('deleteConfirmation-' + answerId).style.display = "block";
    }

    function closeDeleteConfirmation(answerId) {
        document.getElementById('deleteConfirmation-' + answerId).style.display = "none";
    }
</script>

<form action="/submit-answer" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <%--является частью защиты от CSRF (межсайтовой подделки запросов) и представляет собой скрытое поле, которое содержит токен CSRF (кросс-сайт-запрос).--%>
    <input type="hidden" name="questionId" value="${question.id}" />
    <textarea name="answerText" rows="4" cols="50" placeholder="Your Answer"></textarea>
    <input type="submit" value="Submit Answer" />
</form>
<button class="action-button back-button" onclick="window.location.href = '/showAllQuestions'">Back to Main Page</button>
</body>
</html>
