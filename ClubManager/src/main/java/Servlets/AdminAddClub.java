package Servlets;

import com.example.clubManager.models.Club;
import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.hibernate.Session;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/admin/addClub")
@MultipartConfig(maxFileSize = 16177215) // allow files up to 16MB
public class AdminAddClub extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Retrieve all students to populate the president dropdown
            List<Etudiant> etudiants = session.createQuery("FROM Etudiant", Etudiant.class).list();
            request.setAttribute("etudiants", etudiants);
            
            // Also retrieve all clubs to list them with edit/remove actions
            List<Club> clubs = session.createQuery("FROM Club", Club.class).list();
            request.setAttribute("clubs", clubs);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        request.getRequestDispatcher("/pages/admin/adminAddClub.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		ClubService clubService = new ClubService(HibernateUtil.getSessionFactory());
            
            request.setCharacterEncoding("UTF-8");
            
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            String presidentIdStr = request.getParameter("presidentId");
            
            
            int presidentId = Integer.parseInt(presidentIdStr);
            
            // Retrieve the uploaded image file
            Part filePart = request.getPart("image");
            byte[] imageBytes = null;
            if(filePart != null && filePart.getSize() > 0) {
            	try (InputStream inputStream = filePart.getInputStream()) {
            		imageBytes = inputStream.readAllBytes();
            	}

            }
            
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();

                
                // Retrieve the chosen president from the database
                Etudiant president = session.get(Etudiant.class, presidentId);
                
                
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpSession httpsession = httpRequest.getSession(false);
                Utilisateur utilisateur =  (Utilisateur) httpsession.getAttribute("user");
                
                // Create and populate the Club instance
                Club club = new Club();
                club.setNom(nom);
                club.setDescription(description);
                club.setImage(imageBytes);
                club.setPresident(president);
                club.setUtilisateur(utilisateur);
                clubService.saveClub(club);
                clubService.addMemberToClub(club, president);

                
            } catch(Exception ex) {

                throw new ServletException(ex);
            } finally {
                if(session != null) session.close();
            }

            response.sendRedirect(request.getContextPath() + "/admin/clubs?message=Club+added+successfully&success=true");    		
    	}catch (Exception e) {
	        System.out.println(e.getMessage());
	        response.sendRedirect(request.getContextPath() + "/admin/clubs?message=Failed+to+add+the+club&error=true");
	    }
        
    }
}
