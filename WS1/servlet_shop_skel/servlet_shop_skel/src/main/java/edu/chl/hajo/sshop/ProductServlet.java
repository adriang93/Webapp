package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to handle Product pages
 *
 * @author hajo
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {

    private static final String TEMPLATE = "WEB-INF/jsp/template.jspx";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String view = request.getParameter("view");
        IShop shop = (IShop) getServletContext().getAttribute(Keys.SHOP.toString());
     

        
        String title = null;
        String content = null;
       
        // This is navigation only
        if(view!=null){
            switch (view) {
                case "edit":                
                    title = "Edit Product";
                    content = "products/editProduct";
                    
                    long id = Long.parseLong(request.getParameter("id"));
                    Product p = shop.getProductCatalogue().find(id);
                    request.setAttribute("product",p);
                    break;
                    
                case "add":
                    title = "Add Product";
                    content = "products/addProduct";
                    
                default:;
                
            }
            
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.getRequestDispatcher("WEB-INF/jsp/template.jspx").forward(request, response);
        }
        // State change! Then Navigation
        if(action!=null){
            switch(action){
                case "edit":
                    long id = Long.parseLong(request.getParameter("id"));
                            
                    double price = Double.parseDouble(request.getParameter("price"));
                    String name = request.getParameter("name");
                    Product hej = new Product(id,name,price);
                    shop.getProductCatalogue().update(hej);
                    title = "Product";
                    response.sendRedirect("shop?view=products");
                    break;
                case "delete":
                    id = Long.parseLong(request.getParameter("id"));
                    shop.getProductCatalogue().delete(id);
                    response.sendRedirect("shop?view=products");
                    break;
                
                case "add":
                    id = shop.getProductCatalogue().count() +1;
                    price = Double.parseDouble(request.getParameter("price"));
                    name = request.getParameter("name");
                    Product added = new Product (id,name,price);
                    shop.getProductCatalogue().create(added);
                    response.sendRedirect("shop?view=products");
                    
                default:;
            }
        }

       
    }

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
