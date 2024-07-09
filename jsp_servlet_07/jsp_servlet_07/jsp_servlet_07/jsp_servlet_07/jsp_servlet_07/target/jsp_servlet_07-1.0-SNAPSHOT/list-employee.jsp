<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee ABC Company Track</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- <link type="text/css" rel="stylesheet" href="css/list.css"> --%>
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>Employee ABC Company Track</h1>
    </header>
</div>

<div id="container">
    <div id="content">
        <!-- Add Student button -->
        <input type="button" value="Add Employee" onclick="window.location.href='add-employee.jsp'; return false;" class="add-student-button" />
        <form method="GET" action="EmployeeControllerServlet">
            <input type="hidden" name="command" value="SEARCH" />
            <label for="searchName">Search by Name:</label>
            <input type="text" id="searchName" name="searchName" />
            <label for="searchEmployeeId">Search by Employee ID:</label>
            <input type="text" id="searchEmployeeId" name="searchEmployeeId" />
            <input type="submit" value="Search" />
        </form>
        <table>
            <tr>
                <th>No</th>
                <th>Employee Id</th>
                <th>Employee Name</th>
                <th>Birthday</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempEmp" items="${EMP_LIST}">
                <!-- Set up a link for each student -->
                <c:url var="tempLink" value="EmployeeControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="empId" value="${tempEmp.id}"/>
                </c:url>
                <!-- Set up a link to delete a student -->
                <c:url var="deleteLink" value="EmployeeControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="empId" value="${tempEmp.id}"/>
                </c:url>
                <tr>
                    <td>${tempEmp.id}</td>
                    <td>${tempEmp.employee_id}</td>
                    <td>${tempEmp.employee_name}</td>
                    <td>${tempEmp.birthday}</td>
                    <td>${tempEmp.phone_number}</td>
                    <td>${tempEmp.email}</td>
                    <td>
                        <a href="${tempLink}" class="update-link">Update</a> |
                        <a href="${deleteLink}" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
