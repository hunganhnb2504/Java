package hr_manage.ass.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.sql.DataSource;

public class EmployeeDbUtil {
    private DataSource dataSource;

    public EmployeeDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Employee> getEmps() throws Exception {

        List<Employee> emps = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);
//          myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from employee order by id";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int empId = myRs.getInt("id");
                String empid = myRs.getString("employee_id");
                String employee_name = myRs.getString("employee_name");
                Date birthday = myRs.getDate("birthday");
                String phone_number = myRs.getString("phone_number");
                String email = myRs.getString("email");

                // create new student object
                Employee tempEmp = new Employee(empId, empid, employee_name, birthday, phone_number, email);

                // add it to the list of students
                emps.add(tempEmp);
            }

            return emps;
        } finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addEmp(Employee theEmp) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
//          myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql for insert
            String sql = "insert into employee "
                    + "(employee_id, employee_name, birthday, phone_number, email)"
                    + "values (?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setString(1, theEmp.getEmployee_id());
            myStmt.setString(2, theEmp.getEmployee_name());
            // Convert java.util.Date to java.sql.Date
            myStmt.setDate(3, new java.sql.Date(theEmp.getBirthday().getTime()));
            myStmt.setString(4, theEmp.getPhone_number());
            myStmt.setString(5, theEmp.getEmail());

            // execute sql insert
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public Employee getEmp(String theEmpId) throws Exception {

        Employee theEmp = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int empId;

        try {
            // convert student id to int
            empId = Integer.parseInt(theEmpId);

            // get connection to database
//          myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to get selected food
            String sql = "select * from employee where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, empId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                String empid = myRs.getString("employee_id");
                String employee_name = myRs.getString("employee_name");
                Date birthday = myRs.getDate("birthday");
                String phone_number = myRs.getString("phone_number");
                String email = myRs.getString("email");

                // use the studentId during construction
                theEmp = new Employee(empId, empid, employee_name, birthday, phone_number, email);
            } else {
                throw new Exception("Could not find employee id: " + empId);
            }

            return theEmp;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updateEmp(Employee theEmp) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL update statement
            String sql = "update employee "
                    + "set employee_id=?, employee_name=?, birthday=?, phone_number=?, email=? "
                    + "where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theEmp.getEmployee_id());
            myStmt.setString(2, theEmp.getEmployee_name());

            // Convert java.util.Date to java.sql.Date
            myStmt.setDate(3, new java.sql.Date(theEmp.getBirthday().getTime()));

            myStmt.setString(4, theEmp.getPhone_number());
            myStmt.setString(5, theEmp.getEmail());
            myStmt.setInt(6, theEmp.getId());



            // execute SQL statement
            myStmt.executeUpdate(); // use executeUpdate for update statement
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void deleteEmp(String theEmpId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert student id to int
            int empId = Integer.parseInt(theEmpId);

            // get connection to database
//          myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to delete student
            String sql = "delete from employee where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, empId);

            // execute sql statement
            myStmt.execute();
        } finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
    public List<Employee> searchEmps(String searchName, String searchEmployeeId) throws Exception {
        List<Employee> employees = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get connection to database
            String url = "jdbc:mysql://localhost:3306/hr_manage";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL query
            String sql = "SELECT * FROM employee WHERE employee_name LIKE ? AND employee_id LIKE ?";
            myStmt = myConn.prepareStatement(sql);

            // set parameters
            myStmt.setString(1, "%" + searchName + "%");
            myStmt.setString(2, "%" + searchEmployeeId + "%");

            // execute query
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String empid = myRs.getString("employee_id");
                String employee_name = myRs.getString("employee_name");
                Date birthday = myRs.getDate("birthday");
                String phone_number = myRs.getString("phone_number");
                String email = myRs.getString("email");

                // create Employee object
                Employee employee = new Employee(id, empid, employee_name, birthday, phone_number, email);
                employees.add(employee);
            }
        } finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }

        return employees;
    }
}
