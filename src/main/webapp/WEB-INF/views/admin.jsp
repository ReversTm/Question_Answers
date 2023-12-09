<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<%--<security:authorize access="hasRole('ADMIN')">--%>
<head>
    <meta charset="utf-8">
    <title>Admin Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
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
<div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Password</th>
            <th>Roles</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td><c:forEach items="${user.roles}" var="role">${role.authority}; </c:forEach></td>
                <td>
                    <form action="/deleteEmployee" method="get">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="submit" value="Delete" />
                    </form>
                    <%--<input type="button" value="Delete"
                           onclick="window.location.href = '${deleteButton}'"/>
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <button type="submit">Delete</button>--%>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="/">Back to postalcode page</a>

</div>

</body>
<%--</security:authorize>--%>
</html>