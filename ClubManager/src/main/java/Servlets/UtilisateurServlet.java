package Servlets;


import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.UtilisateurService;
import com.example.clubManager.util.HibernateUtil;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@SuppressWarnings("serial")
@WebServlet("/utilisateurs")
public class UtilisateurServlet extends HttpServlet {
	
    private UtilisateurService utilisateurService;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("Initialisation de UtilisateurServlet...");
            utilisateurService = new UtilisateurService(HibernateUtil.getSessionFactory());
            System.out.println("UtilisateurService initialisé avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur init : " + e.getMessage());
            throw new ServletException("Erreur lors de l'initialisation", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
            req.setAttribute("utilisateurs", utilisateurs);
            req.getRequestDispatcher("/pages/utilisateurs.jsp").forward(req, resp);
        } catch (Exception e) {
            System.err.println("Erreur doGet : " + e.getMessage());
            req.setAttribute("error", "Erreur : " + e.getMessage());
            req.getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        String role = req.getParameter("role");

        if (email != null && !email.trim().isEmpty() && motDePasse != null && !motDePasse.trim().isEmpty() && role != null) {
            try {
                Utilisateur utilisateur = new Utilisateur(email, motDePasse, role);
                utilisateurService.saveUtilisateur(utilisateur);
                resp.sendRedirect(req.getContextPath() + "/utilisateurs");
            } catch (Exception e) {
                req.setAttribute("error", "Erreur lors de l'ajout : " + e.getMessage());
                req.getRequestDispatcher("/WEB-INF/pages/ajouterUtilisateur.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Tous les champs sont requis.");
            req.getRequestDispatcher("/WEB-INF/pages/ajouterUtilisateur.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Arrêt de UtilisateurServlet...");
        HibernateUtil.shutdown();
    }
}