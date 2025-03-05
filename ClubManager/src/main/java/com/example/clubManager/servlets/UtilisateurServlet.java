package com.example.clubManager.servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.clubManager.util.HibernateUtil;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.UtilisateurService;
import java.io.IOException;
import java.util.List;

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

    // Gestion des requêtes GET (afficher la liste des utilisateurs)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Contexte : " + req.getContextPath());
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        req.setAttribute("utilisateurs", utilisateurs); // Passage des données à la JSP
        req.getRequestDispatcher("/WEB-INF/pages/utilisateurs.jsp").forward(req, resp); // Transfert vers la JSP
    }

    // Gestion des requêtes POST (ajouter un utilisateur)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        String role = req.getParameter("role");
        Utilisateur utilisateur = new Utilisateur(email, motDePasse, role);
        utilisateurService.saveUtilisateur(utilisateur); // Appel au service pour sauvegarder
        resp.sendRedirect("/utilisateurs"); // Redirection après succès
    }
    @Override
    public void destroy() {
        // Fermeture de la SessionFactory lors de l'arrêt du servlet (optionnel selon le cycle de vie)
        HibernateUtil.shutdown();
    }
}