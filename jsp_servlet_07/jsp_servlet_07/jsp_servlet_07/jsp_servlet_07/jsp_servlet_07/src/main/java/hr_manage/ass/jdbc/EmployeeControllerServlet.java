package hr_manage.ass.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/EmployeeControllerServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10 MB
        maxFileSize = 1024 * 1024 * 50,        // 50 MB
        maxRequestSize = 1024 * 1024 * 100     // 100 MB
)
public class EmployeeControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EmployeeDbUtil employeeDbUtil;

    @Resource(name = "jdbc/hr_manage")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our food db util ... and pass in the conn pool / datasource
        try {
            employeeDbUtil = new EmployeeDbUtil(dataSource);
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
                    addEmp(request, response);
                    break;
                case "LOAD":
                    loadEmp(request, response);
                    break;
                case "UPDATE":
                    updateEmp(request, response);
                    break;
                case "DELETE":
                    deleteEmp(request, response);
                    break;
                case "SEARCH":
                    searchEmps(request, response);
                    break;
                default:
                    listEmps(request, response);
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
                    addEmp(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;

            case "UPDATE":
                try {
                    updateEmp(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            case "SEARCH":
                try {
                    searchEmps(request, response); // Handle search request
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;

            case "DELETE":
                try {
                    deleteEmp(request, response);
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
    private void searchEmps(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // read search name and employee id from form data
        String searchName = request.getParameter("searchName");
        String searchEmployeeId = request.getParameter("searchEmployeeId");

        // search employees from database
        List<Employee> employees = employeeDbUtil.searchEmps(searchName, searchEmployeeId);

        // add employees to the request
        request.setAttribute("EMP_LIST", employees);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
        dispatcher.forward(request, response);
    }
    private void deleteEmp(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // read food id from form data
        String theEmpId = request.getParameter("empId");

        // delete food from database
        employeeDbUtil.deleteEmp(theEmpId);

        // send them back to "list foods" page
        listEmps(request, response);
    }

    private void updateEmp(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Read employee info from form data
        int id = Integer.parseInt(request.getParameter("empId"));
        String empid = request.getParameter("employee_id");
        String employee_name = request.getParameter("employee_name");
        String birthdayStr = request.getParameter("birthday");
        String phone_number = request.getParameter("phone_number");
        String email = request.getParameter("email");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = formatter.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Create a new employee object
        Employee theEmp = new Employee(id, empid, employee_name, birthday, phone_number, email);

        // Perform update on database
        employeeDbUtil.updateEmp(theEmp);

        // Send them back to the "list foods" page
        listEmps(request, response);
    }


    private void loadEmp(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read food id from form data
        String theEmpId = request.getParameter("empId");

        // get food from database (db util)
        Employee theEmp = employeeDbUtil.getEmp(theEmpId);

        // place food in the request attribute
        request.setAttribute("THE_EMP", theEmp);

        // send to jsp page: update-employee-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("update-employee-form.jsp");
        dispatcher.forward(request, response);
    }


    private void addEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read food info from form data
        String empid = request.getParameter("employee_id");
        String employee_name = request.getParameter("employee_name");
        String birthdayStr = request.getParameter("birthday");
        String phone_number = request.getParameter("phone_number");
        String email = request.getParameter("email");

        // Parse the birthday string to a java.util.Date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = formatter.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // create a new food object
        Employee theEmp = new Employee(empid, employee_name, birthday, phone_number, email);

        // add the food to the database
        employeeDbUtil.addEmp(theEmp);

        // send back to main page (the food list)
        listEmps(request, response);
    }

    private void listEmps(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get foods from db util
        List<Employee> emps = employeeDbUtil.getEmps();

        // add foods to the request
        request.setAttribute("EMP_LIST", emps);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
        dispatcher.forward(request, response);
    }
}

