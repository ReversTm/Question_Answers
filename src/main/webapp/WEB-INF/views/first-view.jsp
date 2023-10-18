<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>
<h1>Hello User!</h1>
<br>
<br>
<input type="button" value="Show All Questions"
       onclick="window.location.href = 'showAllQuestions'"/>
<br>
<security:authorize access="hasRole('ADMIN')">
    Only for Admin
    <input type="button" value="ADMIN"
           onclick="window.location.href = 'admin'">
    <br>
</security:authorize>
</body>

</html>