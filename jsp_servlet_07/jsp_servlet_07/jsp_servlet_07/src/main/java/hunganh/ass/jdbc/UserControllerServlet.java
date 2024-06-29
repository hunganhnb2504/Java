package hunganh.ass.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserControllerServlet")

public class UserControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDbUtil userDbUtil;

    @Resource(name = "jdbc/restaurant") // adjust the name as per your context.xml or server configuration
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            userDbUtil = new UserDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "list";
            }
            switch (theCommand) {
                case "LOAD":
                    loadUser(request, response);
                    break;
                case "UPDATE":
                    updateUser(request, response);
                    break;
                case "DELETE":
                    deleteUser(request, response);
                    break;
                case "ADD":
                    addUser(request, response);
                    break;
                default:
                    listUsers(request, response);
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Redirect POST requests to doGet
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = userDbUtil.getUsers();
        request.setAttribute("USER_LIST", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDbUtil.getUser(userId);
        request.setAttribute("THE_USER", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("userId"));
        String fullName = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        String resetToken = request.getParameter("resetToken");
        String resetTokenExpiry = request.getParameter("resetTokenExpiry");

        User user = new User(id, fullName, birthday, email, phone, password, role, address, resetToken, resetTokenExpiry);
        userDbUtil.updateUser(user);

        listUsers(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("userId"));
        userDbUtil.deleteUser(userId);
        listUsers(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fullName = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        String resetToken = request.getParameter("resetToken");
        String resetTokenExpiry = request.getParameter("resetTokenExpiry");

        User user = new User(fullName, birthday, email, phone, password, role, address, resetToken, resetTokenExpiry);
        userDbUtil.addUser(user);

        listUsers(request, response);
    }
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    private String uploadFile(Part part, String fileName, String uploadDirectory) throws IOException {
        File fileUploadDirectory = new File(uploadDirectory);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String filePath = uploadDirectory + File.separator + fileName;
        part.write(filePath);
        return filePath;
    }
}
