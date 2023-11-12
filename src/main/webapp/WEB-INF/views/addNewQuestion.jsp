<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>Question Info</h2>
<br>
<form action="/saveQuestion" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <%--является частью защиты от CSRF (межсайтовой подделки запросов) и представляет собой скрытое поле, которое содержит токен CSRF (кросс-сайт-запрос).--%>
    <input type="hidden" name="questionId" value="${question.id}" />
    <textarea name="answerText" rows="4" cols="50" placeholder="Your Answer"></textarea>
    <input type="submit" value="Submit Question" />
</form>
</body>
</html>