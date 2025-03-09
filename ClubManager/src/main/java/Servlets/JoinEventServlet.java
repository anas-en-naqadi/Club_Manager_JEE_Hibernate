package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.clubManager.models.Evenement;
import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.ParticipantEvenement;
import com.example.clubManager.models.ParticipantEvenementId;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.MembreClubService;
import com.example.clubManager.services.ParticipantEvenementService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/join_event")
public class JoinEventServlet extends HttpServlet {
    private ParticipantEvenementService participantEvenementService;
    private MembreClubService membreClubService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        participantEvenementService = new ParticipantEvenementService(HibernateUtil.getSessionFactory());
        membreClubService = new MembreClubService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        
        Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Get managed entities using current session
                Etudiant etd = session.get(Etudiant.class, user.getEtudiant().getIdEtudiant());
                if (membreClubService.isMember(clubId, etd.getIdEtudiant())) {
                    Evenement event = session.get(Evenement.class, eventId);
                    // Build composite key with the correct parameter order:
                    ParticipantEvenementId peId = new ParticipantEvenementId(etd.getIdEtudiant(), eventId);
                    ParticipantEvenement existing = participantEvenementService.getParticipantEvenementById(peId);

                    if (existing != null) {
                        response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + 
                                          "&message=You+are+already+registered+for+this+event&error=true");
                    } else {
                        // Create new participation
                        ParticipantEvenement newParticipation = new ParticipantEvenement();
                        newParticipation.setIdEvenement(peId.getIdEvenement());
                        newParticipation.setIdParticipant(peId.getIdParticipant());
                        newParticipation.setEvenement(event);
                        newParticipation.setEtudiant(etd);
                        
                        // Save using service
                        participantEvenementService.saveParticipantEvenement(newParticipation);
                        
                        tx.commit();
                        response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + 
                                           "&message=You+have+successfully+joined+the+event&success=true");
                    }                    
                    
                } else {
                    response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + 
                            "&message=Please+join+the+club+first&error=true");                	
                }

            } catch (Exception e) {
                tx.rollback();
                response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + 
                                     "&message=Error+joining+the+event:+" + e.getMessage() + "&error=true");
            }
        }
    }
}
