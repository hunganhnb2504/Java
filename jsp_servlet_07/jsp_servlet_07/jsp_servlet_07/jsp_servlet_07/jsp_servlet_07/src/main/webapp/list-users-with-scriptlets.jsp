<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/list-users-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CodeLean Academy</h2>
    </div>
</div>

<div id="container">
    <h3>User List</h3>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Iterate over the list of users -->
        <c:forEach var="user" items="${requestScope.USER_LIST}">
            <tr>
                <td>${user.id}</td>
                <td>${user.fullName}</td>
                <td>${user.birthday}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.role}</td>
                <td>${user.address}</td>
                <td>
                    <a href="UserControllerServlet?command=LOAD&userId=${user.id}">Update</a>
                    <a href="UserControllerServlet?command=DELETE&userId=${user.id}" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p>
        <a href="add-user-form.jsp">Add New User</a>
    </p>
</div>
</body>
</html>
