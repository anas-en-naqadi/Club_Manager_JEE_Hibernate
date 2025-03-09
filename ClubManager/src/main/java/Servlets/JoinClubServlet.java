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

import com.example.clubManager.models.Club;
import com.example.clubManager.models.Etudiant;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.services.MembreClubService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/join_club")
public class JoinClubServlet extends HttpServlet {
    private ClubService clubService;
    private MembreClubService membreClubService;

    @Override
    public void init() throws ServletException {
        super.init();
        clubService = new ClubService(HibernateUtil.getSessionFactory());
        membreClubService = new MembreClubService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        Utilisateur user = (Utilisateur) httpSession.getAttribute("user");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Get managed entities
                Etudiant etd = session.get(Etudiant.class, user.getEtudiant().getIdEtudiant());
                Club club = session.get(Club.class, clubId);
                if(membreClubService.isMember(clubId, etd.getIdEtudiant())) {
                    response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + "&message=You+are+alredy+member+in+this+club&error=true");
	
                }else {
                    clubService.addMemberToClub(club, etd);
                    tx.commit();
                    response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + "&message=You+have+successfully+joined+the+club&success=true");
                }

            } catch (Exception e) {
                tx.rollback();
                response.sendRedirect(request.getContextPath() + "/club?id=" + clubId + "&message=You have not successfully joined the club&error=true");
            }
        }

    }
}