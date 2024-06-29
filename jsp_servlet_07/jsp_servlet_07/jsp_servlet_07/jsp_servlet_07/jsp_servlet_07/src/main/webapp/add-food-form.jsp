<!DOCTYPE html>
<html>
<head>
    <title>Add Food</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Restran Admin</h2>
    </div>
</div>

<div id="container">
    <h3>Add Food</h3>
    <form method="POST" action="FoodControllerServlet" enctype="multipart/form-data">
        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" id="name" name="name" required /></td>
            </tr>
            <tr>
                <td><label for="categoryId">Category:</label></td>
                <td><input type="number" id="categoryId" name="categoryId" required /></td>
            </tr>
            <tr>
                <td><label for="image">Image:</label></td>
                <td><input type="file" id="image" name="image" accept="image/*" required /></td>
            </tr>
            <tr>
                <td><label for="description">Description:</label></td>
                <td><input type="text" id="description" name="description" required /></td>
            </tr>
            <tr>
                <td><label for="quantity">Quantity:</label></td>
                <td><input type="number" id="quantity" name="quantity" required /></td>
            </tr>
            <tr>
                <td><label for="price">Price:</label></td>
                <td><input type="number" step="0.01" id="price" name="price" required /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>
            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="FoodControllerServlet">Back to List</a>
    </p>
</div>
</body>
</html>
