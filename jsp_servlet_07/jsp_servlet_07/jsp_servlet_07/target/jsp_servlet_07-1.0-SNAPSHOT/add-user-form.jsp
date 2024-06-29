<!DOCTYPE html>
<html>

<head>
    <title>Add User</title>
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
    <h3>Add User</h3>

    <form action="UserControllerServlet" method="POST">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>Full name:</label></td>
                <td><input type="text" name="fullName" required /></td>
            </tr>

            <tr>
                <td><label>Birthday:</label></td>
                <td><input type="date" name="birthday" /></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="email" name="email" required /></td>
            </tr>

            <tr>
                <td><label>Phone:</label></td>
                <td><input type="text" name="phone" /></td>
            </tr>

            <tr>
                <td><label>Password:</label></td>
                <td><input type="password" name="password" required /></td>
            </tr>

            <tr>
                <td><label>Role:</label></td>
                <td><input type="text" name="role" required /></td>
            </tr>

            <tr>
                <td><label>Address:</label></td>
                <td><input type="text" name="address" /></td>
            </tr>

            <tr>
                <td><label>Reset Token:</label></td>
                <td><input type="text" name="resetToken" /></td>
            </tr>

            <tr>
                <td><label>Reset Token Expiry:</label></td>
                <td><input type="datetime-local" name="resetTokenExpiry" /></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" value="Add User" class="save" /></td>
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
