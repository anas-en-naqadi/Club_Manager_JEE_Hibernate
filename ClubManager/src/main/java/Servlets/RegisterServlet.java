package Servlets;

import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("currentPage", "register");
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String cne = request.getParameter("cne");
        String nomComplet = request.getParameter("nomComplet");
        String filliere = request.getParameter("filliere");
        String faculte = request.getParameter("faculte");

        if (!password.equals(confirmPassword)) {
            response.sendRedirect(request.getContextPath() + "/register?error=Password+mismatch");
            return;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Utilisateur user = new Utilisateur();
            user.setEmail(email);
            user.setMotDePasse(password);
            user.setRole("student");

            Etudiant etudiant = new Etudiant();
            etudiant.setCne(cne);
            etudiant.setNomComplet(nomComplet);
            etudiant.setFilliere(filliere);
            etudiant.setFaculte(faculte);
            etudiant.setUtilisateur(user);
            
            user.setEtudiant(etudiant);

            session.persist(user);
            transaction.commit();

            response.sendRedirect(request.getContextPath() + "/login?registration=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + 
                "/register?error=Registration+failed:+${e.getMessage()}");
        }
    }
}