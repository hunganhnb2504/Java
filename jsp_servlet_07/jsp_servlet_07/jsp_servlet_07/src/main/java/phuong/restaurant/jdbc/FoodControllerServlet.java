package phuong.restaurant.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/FoodControllerServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10 MB
        maxFileSize = 1024 * 1024 * 50,        // 50 MB
        maxRequestSize = 1024 * 1024 * 100     // 100 MB
)
public class FoodControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FoodDbUtil foodDbUtil;

    @Resource(name = "jdbc/restaurant")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our food db util ... and pass in the conn pool / datasource
        try {
            foodDbUtil = new FoodDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            // if the command is missing, then default to listing foods
            if(theCommand == null)
                theCommand = "list";
            // route to the appropriate method
            switch (theCommand) {
                case "ADD":
                    addFood(request, response);
                    break;
                case "LOAD":
                    loadFood(request, response);
                    break;
                case "UPDATE":
                    updateFood(request, response);
                    break;
                case "DELETE":
                    deleteFood(request, response);
                    break;
                default:
                    listFoods(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            command = "LIST";
        }

        switch (command) {
            case "ADD":
                try {
                    addFood(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;

            case "UPDATE":
                try {
                    updateFood(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            // Other cases...

            default:
                // Your default action...
                break;
        }
    }

    private void deleteFood(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // read food id from form data
        String theFoodId = request.getParameter("foodId");

        // delete food from database
        foodDbUtil.deleteFood(theFoodId);

        // send them back to "list foods" page
        listFoods(request, response);
    }

    private void updateFood(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Read food info from form data
        int id = Integer.parseInt(request.getParameter("foodId"));
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        // Handle image upload
        Part filePart = request.getPart("image");
        String fileName = getSubmittedFileName(filePart);

        String image = null;

        // Check if a new image was uploaded
        if (fileName != null && !fileName.isEmpty()) {
            // Get the real path for webapp/images
            String uploadDir = getServletContext().getRealPath("/") + "images";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String filePath = uploadDir + File.separator + fileName;

            // Save the new file on server
            filePart.write(filePath);

            // Set the new image file name
            image = fileName;
        } else {
            // If no new image is uploaded, retain the existing image filename
            image = request.getParameter("existingImage");
        }

        // Create a new food object
        Food theFood = new Food(id, name, image, description, quantity, price, categoryId);

        // Perform update on database
        foodDbUtil.updateFood(theFood);

        // Send them back to the "list foods" page
        listFoods(request, response);
    }


    private void loadFood(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read food id from form data
        String theFoodId = request.getParameter("foodId");

        // get food from database (db util)
        Food theFood = foodDbUtil.getFood(theFoodId);

        // place food in the request attribute
        request.setAttribute("THE_FOOD", theFood);

        // send to jsp page: update-food-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Restaurant/update-food-form.jsp");
        dispatcher.forward(request, response);
    }


    private void addFood(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read food info from form data
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        // Handle image upload
        Part filePart = request.getPart("image");
        String fileName = getSubmittedFileName(filePart);
        // Get the real path for webapp/images
        String uploadDir = getServletContext().getRealPath("/images");
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String filePath = uploadDir + File.separator + fileName;

        // Save the file on server
        filePart.write(filePath);

        // create a new food object
        Food theFood = new Food(name, fileName, description, quantity, price, categoryId);

        // add the food to the database
        foodDbUtil.addFood(theFood);

        // send back to main page (the food list)
        listFoods(request, response);
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf(File.separator) + 1).replace("\"", "");
            }
        }
        return null;
    }

    private void listFoods(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get foods from db util
        List<Food> foods = foodDbUtil.getFoods();

        // add foods to the request
        request.setAttribute("FOOD_LIST", foods);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Restaurant/list-food-with-img.jsp");
        dispatcher.forward(request, response);
    }
}
