<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>
<h1>Hello ${userName}!</h1>
<br>
<br>

<input type="button" value="Answer Question"
       onclick="window.location.href = 'showAllQuestions'"/>
<br>
<input type="button" value="Ask Question"
       onclick="window.location.href = 'addNewQuestion'"/>

<security:authorize access="hasRole('ADMIN')">
    Only for Admin
    <input type="button" value="ADMIN"
           onclick="window.location.href = 'admin'">
    <br>
</security:authorize>
</body>

</html>