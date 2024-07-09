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
        <h2>Employee Managment</h2>
    </div>
</div>

<div id="container">
    <h3>Add Employee</h3>
    <form method="POST" action="EmployeeControllerServlet" enctype="multipart/form-data">
        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label for="employee_id">Employee Id:</label></td>
                <td><input type="text" id="employee_id" name="employee_id" required /></td>
            </tr>
            <tr>
                <td><label for="employee_name">Employee Name:</label></td>
                <td><input type="text" id="employee_name" name="employee_name" required /></td>
            </tr>
            <tr>
                <td><label for="birthday">Birthday:</label></td>
                <td><input type="date" id="birthday" name="birthday" required /></td>
            </tr>
            <tr>
                <td><label for="phone_number">Phone Number:</label></td>
                <td><input type="text" id="phone_number" name="phone_number" required /></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" id="email" name="email" required /></td>
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
        <a href="EmployeeControllerServlet">Back to List</a>
    </p>
</div>
</body>
</html>
