<!DOCTYPE html>
<html>

<head>
    <title>Update User</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>CodeLean Academy</h2>
    </div>
</div>

<div id="container">
    <h3>Update User</h3>

    <form action="UserControllerServlet" method="POST">
        <input type="hidden" name="command" value="UPDATE" />
        <input type="hidden" name="userId" value="${THE_USER.id}" />

        <table>
            <tbody>
            <tr>
                <td><label>Full name:</label></td>
                <td><input type="text" name="fullName" value="${THE_USER.fullName}" /></td>
            </tr>
            <tr>
                <td><label>Birthday:</label></td>
                <td><input type="text" name="birthday" value="${THE_USER.birthday}" /></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email" value="${THE_USER.email}" /></td>
            </tr>
            <tr>
                <td><label>Phone:</label></td>
                <td><input type="text" name="phone" value="${THE_USER.phone}" /></td>
            </tr>
            <tr>
                <td><label>Password:</label></td>
                <td><input type="password" name="password" value="${THE_USER.password}" /></td>
            </tr>
            <tr>
                <td><label>Role:</label></td>
                <td><input type="text" name="role" value="${THE_USER.role}" /></td>
            </tr>
            <tr>
                <td><label>Address:</label></td>
                <td><input type="text" name="address" value="${THE_USER.address}" /></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>
            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="UserControllerServlet">Back to List</a>
    </p>
</div>
</body>
</html>
