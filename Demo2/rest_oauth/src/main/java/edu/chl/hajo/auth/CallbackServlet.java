package edu.chl.hajo.auth;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.AccessGrant;
import org.brickred.socialauth.util.SocialAuthUtil;

/**
 * This is called from Twitter (a redirect)
 *
 * @author hajo
 */
@WebServlet(name = "CallbackServlet", urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CallbackServlet.class.getSimpleName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.log(Level.INFO, "*** CallbackServlet");
        AccessGrant ag;
        HttpSession session = request.getSession();
        try {
            Map<String, String> paramsMap = SocialAuthUtil.getRequestParametersMap(request);
            // Must use same manager as used for login
            SocialAuthManager manager = (SocialAuthManager) session.getAttribute("authManager");
            LOG.log(Level.INFO, "*** Callback authManager is {0}", manager);
            AuthProvider provider = manager.connect(paramsMap);
            LOG.log(Level.INFO, "*** Callback provider is {0}", provider);
            ag = provider.getAccessGrant();
            LOG.log(Level.INFO, "*** Callback got access grant");
        } catch (Exception ex) {
            //ex.printStackTrace();
            response.sendRedirect("error.html");
            return;
        }
        // Clear state
        session.invalidate();

        Cookie cookie = new Cookie("key", ag.getKey());
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.sendRedirect("home.html");

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
