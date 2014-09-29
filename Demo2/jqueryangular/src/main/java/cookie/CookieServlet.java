package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hajo
 */
@WebServlet(name = "CookieServlet", urlPatterns = {"/cookie"})
public class CookieServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CookieServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

           
       
        LOG.log(Level.INFO, "This is CookieServlet");
        String animal = request.getParameter("animal"); 
        LOG.log(Level.INFO, "Incoming data is {0}", animal);
        Cookie[] cs = request.getCookies();
        LOG.log(Level.INFO, "Cookies " + cs);
        // Domain will be null for localhost
        for (Cookie c : cs) {
            LOG.log(Level.INFO, "{0} {1} {2}", new Object[]{c.getDomain(), c.getName(), c.getValue()});
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
