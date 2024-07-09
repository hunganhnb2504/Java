<!DOCTYPE html>
<html>

<head>
    <title>Edit Employee</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>ABC Comany Update Employee</h2>
    </div>
</div>

<div id="container">
    <h3>Update Employee</h3>

    <form method="POST" action="EmployeeControllerServlet" enctype="multipart/form-data">

        <input type="hidden" name="command" value="UPDATE" />

        <input type="hidden" name="empId" value="${THE_EMP.id}" />

        <table>
            <tbody>

            <tr>
                <td><label> Employee Id:</label></td>
                <td><input type="text" name="employee_id"
                           value="${THE_EMP.employee_id}" /></td>
            </tr>

            <tr>
                <td><label> Employee Name:</label></td>
                <td><input type="text" name="employee_name"
                           value="${THE_EMP.employee_name}" /></td>
            </tr>

            <tr>
                <td><label>Birthday</label></td>
                <td><input type="date" name="birthday"
                           value="${THE_EMP.birthday}" /></td>
            </tr>

            <tr>
                <td><label>Phone Number:</label></td>
                <td><input type="text" name="phone_number"
                           value="${THE_EMP.phone_number}" /></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email"
                           value="${THE_EMP.email}" /></td>
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
        <a href="EmployeeControllerServlet">Back to List</a>
    </p>
</div>
</body>

</html>











