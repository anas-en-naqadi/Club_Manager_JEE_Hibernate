package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Servlet implementation class RegisterServlet
 */
@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.setAttribute("currentPage", "register");
    	
        if (request.getSession(false) != null && request.getSession().getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        Map<String, String> errors = new HashMap<>();

        // Validation
        if (!password.equals(confirmPassword)) {
            errors.put("passwordMismatch", "Les mots de passe ne correspondent pas");
        }
        if (!email.endsWith("@ucd.ac.ma")) {
            errors.put("invalidEmail", "Email universitaire invalide");
        }
        //if (UserService.emailExists(email)) {
            //errors.put("emailExists", "Un compte existe déjà avec cet email");
        //}
        //if (!validatePassword(password)) {
            //errors.put("weakPassword", "Le mot de passe ne respecte pas les exigences de sécurité");
        //}

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("formData", Map.of(
                "firstName", firstName,
                "lastName", lastName,
                "email", email
            ));
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        try {
            //User newUser = UserService.createUser(firstName, lastName, email, password);
            request.getSession().setAttribute("successMessage", "Inscription réussie ! Veuillez vous connecter");
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            errors.put("registrationError", "Erreur lors de l'inscription");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }

    @SuppressWarnings("unused")
	private boolean validatePassword(String password) {
        return password.length() >= 8 
            && password.matches(".*[A-Z].*") 
            && password.matches(".*\\d.*");
    }
}