package hunganh.ass.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDbUtil {
    private DataSource dataSource;

    public UserDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL query
            String sql = "SELECT * FROM user ORDER BY fullName";
            myStmt = myConn.createStatement();

            // Execute query
            myRs = myStmt.executeQuery(sql);

            // Process result set
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String fullName = myRs.getString("FullName");
                String birthday = myRs.getString("Birthday");
                String email = myRs.getString("Email");
                String phone = myRs.getString("Phone");
                String password1 = myRs.getString("Password");
                String role = myRs.getString("Role");
                String address = myRs.getString("Address");
                String resetToken = myRs.getString("ResetToken");
                String resetTokenExpiry = myRs.getString("ResetTokenExpiry");

                User tempUser = new User(id, fullName, birthday, email, phone, password1, role, address, resetToken, resetTokenExpiry);
                users.add(tempUser);
            }
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, myRs);
        }

        return users;
    }

    public void addUser(User theUser) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL insert statement
            String sql = "INSERT INTO user (fullName, birthday, email, phone, password, role, address, resetToken, resetTokenExpiry) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // Set parameters
            myStmt.setString(1, theUser.getFullName());
            myStmt.setString(2, theUser.getBirthday());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPhone());
            myStmt.setString(5, theUser.getPassword());
            myStmt.setString(6, theUser.getRole());
            myStmt.setString(7, theUser.getAddress());
            myStmt.setString(8, theUser.getResetToken());
            myStmt.setString(9, theUser.getResetTokenExpiry());

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    public User getUser(int userId) throws Exception {
        User theUser = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL query to retrieve user by ID
            String sql = "SELECT * FROM user WHERE id=?";
            myStmt = myConn.prepareStatement(sql);

            // Set parameter
            myStmt.setInt(1, userId);

            // Execute query
            myRs = myStmt.executeQuery();

            // Process result set
            if (myRs.next()) {
                int id = myRs.getInt("id");
                String fullName = myRs.getString("fullName");
                String birthday = myRs.getString("birthday");
                String email = myRs.getString("email");
                String phone = myRs.getString("phone");
                String passwordDb = myRs.getString("password");
                String role = myRs.getString("role");
                String address = myRs.getString("address");
                String resetToken = myRs.getString("resetToken");
                String resetTokenExpiry = myRs.getString("resetTokenExpiry");

                // Create User object
                theUser = new User(id, fullName, birthday, email, phone, passwordDb, role, address, resetToken, resetTokenExpiry);
            } else {
                throw new Exception("User ID not found: " + userId);
            }
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, myRs);
        }

        return theUser;
    }

    public void updateUser(User theUser) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL update statement
            String sql = "UPDATE user "
                    + "SET fullName=?, birthday=?, email=?, phone=?, password=?, role=?, address=?, resetToken=?, resetTokenExpiry=? "
                    + "WHERE id=?";

            myStmt = myConn.prepareStatement(sql);

            // Set parameters
            myStmt.setString(1, theUser.getFullName());
            myStmt.setString(2, theUser.getBirthday());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPhone());
            myStmt.setString(5, theUser.getPassword());
            myStmt.setString(6, theUser.getRole());
            myStmt.setString(7, theUser.getAddress());
            myStmt.setString(8, theUser.getResetToken());
            myStmt.setString(9, theUser.getResetTokenExpiry());
            myStmt.setInt(10, theUser.getId());

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    public void deleteUser(int userId) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            // SQL delete statement
            String sql = "DELETE FROM user WHERE id=?";

            myStmt = myConn.prepareStatement(sql);

            // Set parameter
            myStmt.setInt(1, userId);

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
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
                myConn.close(); // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
