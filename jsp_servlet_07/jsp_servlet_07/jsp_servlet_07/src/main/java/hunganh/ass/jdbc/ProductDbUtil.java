package hunganh.ass.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDbUtil {
    private DataSource dataSource;

    public ProductDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Product> getProducts() throws Exception {
        List<Product> products = new ArrayList<>();
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
            String sql = "SELECT * FROM product ORDER BY name";
            myStmt = myConn.createStatement();

            // Execute query
            myRs = myStmt.executeQuery(sql);

            // Process result set
            while (myRs.next()) {
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String price = myRs.getString("price");
                String image = myRs.getString("image");

                Product tempProduct = new Product(id, name, price, image);
                products.add(tempProduct);
            }
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, myRs);
        }

        return products;
    }

    public void addProduct(Product theProduct) throws Exception {
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
            String sql = "INSERT INTO product (name, price, image) VALUES (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // Set parameters
            myStmt.setString(1, theProduct.getName());
            myStmt.setString(2, theProduct.getPrice());
            myStmt.setString(3, theProduct.getImage());

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    public Product getProduct(int productId) throws Exception {
        Product theProduct = null;
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

            // SQL query to retrieve product by ID
            String sql = "SELECT * FROM product WHERE id=?";
            myStmt = myConn.prepareStatement(sql);

            // Set parameter
            myStmt.setInt(1, productId);

            // Execute query
            myRs = myStmt.executeQuery();

            // Process result set
            if (myRs.next()) {
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String price = myRs.getString("price");
                String image = myRs.getString("image");

                // Create Product object
                theProduct = new Product(id, name, price, image);
            } else {
                throw new Exception("Product ID not found: " + productId);
            }
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, myRs);
        }

        return theProduct;
    }

    public void updateProduct(Product theProduct) throws Exception {
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
            String sql = "UPDATE product SET name=?, price=?, image=? WHERE id=?";

            myStmt = myConn.prepareStatement(sql);

            // Set parameters
            myStmt.setString(1, theProduct.getName());
            myStmt.setString(2, theProduct.getPrice());
            myStmt.setString(3, theProduct.getImage());
            myStmt.setInt(4, theProduct.getId());

            // Execute SQL statement
            myStmt.execute();
        } finally {
            // Close JDBC objects in finally block
            close(myConn, myStmt, null);
        }
    }

    public void deleteProduct(int productId) throws Exception {
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
            String sql = "DELETE FROM product WHERE id=?";

            myStmt = myConn.prepareStatement(sql);

            // Set parameter
            myStmt.setInt(1, productId);

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
