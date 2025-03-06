package Servlets;

import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("currentPage", "login");
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Utilisateur user = session.createQuery(
                    "FROM Utilisateur WHERE email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (user != null && user.getMotDePasse().equals(password)) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user);
                if(user.getRole().equals("admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin/clubs");
                }else {
                    response.sendRedirect(request.getContextPath() + "/my_clubs");    	
                }

            } else {
                response.sendRedirect(request.getContextPath() + "/login?error=Identifiants+invalides");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?error=Erreur+de+connexion");
        }
    }
}