package com.example.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDbUtil userDbUtil;

    @Resource(name = "jdbc/user_manager")
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
                theCommand = "LIST";
            }

            switch (theCommand) {
                case "ADD":
                    addUser(request, response);
                    break;
                case "LOAD":
                    loadUser(request, response);
                    break;
                case "UPDATE":
                    updateUser(request, response);
                    break;
                case "DELETE":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<User> users = userDbUtil.getUsers();
        request.setAttribute("USER_LIST", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fullName = request.getParameter("fullName");
        LocalDateTime birthday = LocalDateTime.parse(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        String resetToken = request.getParameter("resetToken");
        LocalDateTime resetTokenExpiry = LocalDateTime.parse(request.getParameter("resetTokenExpiry"));

        User newUser = new User(fullName, birthday, email, phone, password, role, address, resetToken, resetTokenExpiry);
        userDbUtil.addUser(newUser);

        listUsers(request, response);
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String theUserId = request.getParameter("userId");
        User theUser = userDbUtil.getUser(theUserId);
        request.setAttribute("THE_USER", theUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("userId"));
        String fullName = request.getParameter("fullName");
        LocalDateTime birthday = LocalDateTime.parse(request.getParameter("birthday"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        String resetToken = request.getParameter("resetToken");
        LocalDateTime resetTokenExpiry = LocalDateTime.parse(request.getParameter("resetTokenExpiry"));

        User theUser = new User(id, fullName, birthday, email, phone, password, role, address, resetToken, resetTokenExpiry);
        userDbUtil.updateUser(theUser);

        listUsers(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String theUserId = request.getParameter("userId");
        userDbUtil.deleteUser(theUserId);
        listUsers(request, response);
    }
}
}
