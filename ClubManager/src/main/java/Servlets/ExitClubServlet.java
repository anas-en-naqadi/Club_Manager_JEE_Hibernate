package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.example.clubManager.models.MembreClubId;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.MembreClubService;
import com.example.clubManager.services.ParticipantEvenementService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/exit_club")
public class ExitClubServlet extends HttpServlet {
    
    private MembreClubService membreClubService;
    private ParticipantEvenementService participantEvenementService;

    @Override
    public void init() throws ServletException {
        super.init();
        membreClubService = new MembreClubService(HibernateUtil.getSessionFactory());
        participantEvenementService = new ParticipantEvenementService(HibernateUtil.getSessionFactory());
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if(user == null || user.getEtudiant() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // Check if user is member of the club
            boolean isMember = membreClubService.isMember(
                clubId, 
                user.getEtudiant().getIdEtudiant()
            );

            if(!isMember) {
                response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + "?massage=You are not a member of this club&error=true");
                return;
            }

            // Create composite ID
            MembreClubId membershipId = new MembreClubId(
                user.getEtudiant().getIdEtudiant(),
                clubId
            );

            // Delete the membership
            membreClubService.deleteMembreClub(membershipId);
            
            // Remove from all club events
            participantEvenementService.deleteByClubAndEtudiant(
                clubId, 
                user.getEtudiant().getIdEtudiant()
            );

            response.sendRedirect(request.getContextPath() + "/my_clubs?message=You have successfully left the club&success=true");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + "?message=Error leaving club&error=true");
        }
    }
}