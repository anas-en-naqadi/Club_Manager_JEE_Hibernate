package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */



@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
        request.setAttribute("currentPage", "login");

        // Vérifier si déjà connecté
        if (request.getSession(false) != null && request.getSession().getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    @SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean rememberMe = "on".equals(request.getParameter("remember"));

        try {
            //User user = UserService.authenticate(email, password);
            //user != null
            if (true) {
                HttpSession session = (HttpSession) request.getSession();
                //session.setAttribute("user", user);
                
                if (rememberMe) {
                    // Implémenter la logique "Se souvenir de moi" avec des cookies
                }
                
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("errorMessage", "Email ou mot de passe incorrect");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erreur lors de la connexion");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}
