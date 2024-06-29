<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>Product Admin</h1>
    </header>
</div>

<div id="container">
    <div id="content">
        <!-- Add Product button -->
        <input type="button" value="Add Product" onclick="window.location.href='product-form.jsp'; return false;" class="add-product-button" />

        <table>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Image</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempProduct" items="${PRODUCT_LIST}">
                <!-- Set up a link for each product -->
                <c:url var="updateLink" value="ProductControllerServlet">
                    <c:param name="action" value="load"/>
                    <c:param name="id" value="${tempProduct.id}"/>
                </c:url>
                <!-- Set up a link to delete a product -->
                <c:url var="deleteLink" value="ProductControllerServlet">
                    <c:param name="action" value="delete"/>
                    <c:param name="id" value="${tempProduct.id}"/>
                </c:url>
                <tr>
                    <td>${tempProduct.name}</td>
                    <td>${tempProduct.price}</td>
                    <td>
                        <img src="${tempProduct.image}" alt="${tempProduct.name}" width="100" height="100"/>
                    </td>
                    <td>
                        <a href="${updateLink}" class="update-link">Update</a> |
                        <a href="${deleteLink}" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this product?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
