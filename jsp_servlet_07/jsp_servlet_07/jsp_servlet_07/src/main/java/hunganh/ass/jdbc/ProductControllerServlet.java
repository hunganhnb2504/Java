package hunganh.ass.jdbc;

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
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductControllerServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDbUtil productDbUtil;

    @Resource(name = "jdbc/restaurant") // adjust the name as per your context.xml or server configuration
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            productDbUtil = new ProductDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "list";
            }
            switch (action) {
                case "create":
                    addProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "load":
                    loadProduct(request, response);
                    break;
                default:
                    listProducts(request, response);
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> products = productDbUtil.getProducts();
        request.setAttribute("PRODUCT_LIST", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productDbUtil.getProduct(productId);
        request.setAttribute("PRODUCT", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);
        String uploadDirectory = request.getServletContext().getRealPath("/images");
        String filePath = uploadFile(filePart, fileName, uploadDirectory);
        String imageUrl = "images/" + fileName;

        Product product = new Product(name, price, imageUrl);
        productDbUtil.addProduct(product);

        listProducts(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);
        String uploadDirectory = request.getServletContext().getRealPath("/images");
        String filePath = uploadFile(filePart, fileName, uploadDirectory);
        String imageUrl = "images/" + fileName;

        Product product = new Product(id, name, price, imageUrl);
        productDbUtil.updateProduct(product);

        listProducts(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("id"));
        productDbUtil.deleteProduct(productId);
        listProducts(request, response);
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

