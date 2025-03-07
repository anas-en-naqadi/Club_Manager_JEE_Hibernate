package Servlets;

import com.example.clubManager.models.Club;
import com.example.clubManager.models.Etudiant;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.services.EtudiantService;
import com.example.clubManager.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/admin/editClub")
@MultipartConfig
public class AdminEditClubServlet extends HttpServlet {
    private final ClubService clubService = new ClubService(HibernateUtil.getSessionFactory());
    private final EtudiantService etudiantService = new EtudiantService(HibernateUtil.getSessionFactory());
    private int clubId;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            clubId = Integer.parseInt(request.getParameter("id"));
            Club club = clubService.getClubById(clubId);
            List<Etudiant> etudiants = etudiantService.getAllEtudiants();

            if (club != null) {
                request.setAttribute("club", club);
                request.setAttribute("etudiants", etudiants);
                request.getRequestDispatcher("/pages/admin/adminEditClub.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Club club = clubService.getClubById(clubId);

            if (club != null) {
            	
                // Update basic fields
                club.setNom(request.getParameter("nom"));
                club.setDescription(request.getParameter("description"));

                // Update president
                int presidentId = Integer.parseInt(request.getParameter("presidentId"));
                Etudiant newPresident = etudiantService.getEtudiantById(presidentId);
                if (newPresident != null) {
                    club.setPresident(newPresident);
                }

                // Handle image upload
                Part filePart = request.getPart("image");
                if (filePart != null && filePart.getSize() > 0) {
                    InputStream fileContent = filePart.getInputStream();
                    byte[] imageBytes = fileContent.readAllBytes();
                    club.setImage(imageBytes);
                }

                clubService.updateClub(club);
                response.sendRedirect(request.getContextPath() + "/admin/clubs?message=club+updated+successfully&success=true");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/clubs?message=club+not+updated+successfully&error=true");
        }
    }
}