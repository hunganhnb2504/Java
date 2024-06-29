package phuong.restaurant.jdbc;

import thidk.codelean.jdbc.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
public class FoodDbUtil {
    private DataSource dataSource;

    public FoodDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Food> getFoods() throws Exception {

        List<Food> foods = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);
//			myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from food order by id";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                int categoryId = myRs.getInt("categoryId");
                String image = myRs.getString("image");
                String description = myRs.getString("description");
                int quantity = myRs.getInt("quantity");
                double price = myRs.getDouble("price");

                // create new student object
                Food tempFood = new Food(id, name, image, description, quantity, price,categoryId);

                // add it to the list of students
                foods.add(tempFood);
            }

            return foods;
        }
        finally {
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
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addFood(Food theFood) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql for insert
            String sql = "insert into food "
                    + "(categoryId, name, image, description,quantity, price)"
                    + "values (?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setInt(1, theFood.getCategoryId());
            myStmt.setString(2, theFood.getName());
            myStmt.setString(3, theFood.getImage());
            myStmt.setString(4, theFood.getDescription());
            myStmt.setInt(5, theFood.getQuantity());
            myStmt.setDouble(6, theFood.getPrice());

            // execute sql insert
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public Food getFood(String theFoodId) throws Exception {

        Food theFood = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int foodId;

        try {
            // convert student id to int
            foodId = Integer.parseInt(theFoodId);

            // get connection to database
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to get selected food
            String sql = "select * from food where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, foodId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                String name = myRs.getString("name");
                String image = myRs.getString("image");
                String description = myRs.getString("description");
                Integer quantity = myRs.getInt("quantity");
                Double price = myRs.getDouble("price");
                Integer categoryId = myRs.getInt("categoryId");

                // use the studentId during construction
                theFood = new Food(foodId, name, image, description, quantity, price, categoryId);
            }
            else {
                throw new Exception("Could not find food id: " + foodId);
            }

            return theFood;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updateFood(Food theFood) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL update statement
            String sql = "update food "
                    + "set name=?, image=?, description=?, quantity=?, price=?, categoryId=? "
                    + "where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theFood.getName());
            myStmt.setString(2, theFood.getImage());
            myStmt.setString(3, theFood.getDescription());
            myStmt.setInt(4, theFood.getQuantity());
            myStmt.setDouble(5, theFood.getPrice());
            myStmt.setInt(6, theFood.getCategoryId()); // set categoryId
            myStmt.setInt(7, theFood.getId()); // set id for the WHERE clause

            // execute SQL statement
            myStmt.executeUpdate(); // use executeUpdate for update statement
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }


    public void deleteFood(String theFoodId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert student id to int
            int foodId = Integer.parseInt(theFoodId);

            // get connection to database
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/restaurant";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to delete student
            String sql = "delete from food where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, foodId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}
