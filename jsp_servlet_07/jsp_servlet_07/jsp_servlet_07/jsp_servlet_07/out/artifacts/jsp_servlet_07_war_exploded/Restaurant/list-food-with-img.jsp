<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Restran Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- <link type="text/css" rel="stylesheet" href="css/list.css"> --%>
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>Restran Admin</h1>
    </header>
</div>

<div id="container">
    <div id="content">
        <!-- Add Student button -->
        <input type="button" value="Add Food" onclick="window.location.href='add-food-form.jsp'; return false;" class="add-student-button" />

        <table>
            <tr>
                <th>Name</th>
                <th>Category ID</th>
                <th>Image</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempFood" items="${FOOD_LIST}">
                <!-- Set up a link for each student -->
                <c:url var="tempLink" value="FoodControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="foodId" value="${tempFood.id}"/>
                </c:url>
                <!-- Set up a link to delete a student -->
                <c:url var="deleteLink" value="FoodControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="foodId" value="${tempFood.id}"/>
                </c:url>
                <tr>
                    <td>${tempFood.name}</td>
                    <td>${tempFood.categoryId}</td>
                    <td>
                        <img src="images/${tempFood.image}" alt="${tempFood.name}" width="100" height="100"/>
                    </td>
                    <td>${tempFood.description}</td>
                    <td>${tempFood.quantity}</td>
                    <td>${tempFood.price}</td>
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
