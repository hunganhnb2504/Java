<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        form {
            width: 300px;
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="file"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        input[type="submit"],
        a.button {
            display: inline-block;
            background-color: #4CAF50;
            color: #fff;
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover,
        a.button:hover {
            background-color: #45a049;
        }

        a.button {
            margin-left: 5px;
        }
    </style>
</head>
<body>
<h1>Product Form</h1>
<c:choose>
    <c:when test="${empty PRODUCT.id}">
        <form method="POST" action="ProductControllerServlet?action=create" enctype="multipart/form-data">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
            <label for="price">Price:</label>
            <input type="text" id="price" name="price">
            <label for="image">Image:</label>
            <input type="file" id="image" name="image">
            <input type="submit" value="Create">
            <a class="button" href="ProductControllerServlet">Cancel</a>
        </form>
    </c:when>
    <c:otherwise>
        <form method="POST" action="ProductControllerServlet?action=update" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${PRODUCT.id}">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${PRODUCT.name}">
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="${PRODUCT.price}">
            <label for="image">Image:</label>
            <input type="file" id="image" name="image">
            <input type="submit" value="Update">
            <a class="button" href="ProductControllerServlet">Cancel</a>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
