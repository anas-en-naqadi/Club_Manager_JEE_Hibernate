package Servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.example.clubManager.models.Club;
import com.example.clubManager.models.Evenement;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.services.MembreClubService;
import com.example.clubManager.services.ParticipantEvenementService;
import com.example.clubManager.util.HibernateUtil;
import java.util.*;



/**
 * Servlet implementation class ClubDetailServlet
 */
@SuppressWarnings("serial")
@WebServlet("/club")
public class ClubDetailServlet extends HttpServlet {
    private MembreClubService membreClubService;

    
    
    @Override
    public void init() throws ServletException {
        super.init();
        membreClubService = new MembreClubService(HibernateUtil.getSessionFactory());
    }
    
    
    
    
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	
        request.setAttribute("currentPage", "club");
        try {
            int clubId = Integer.parseInt(request.getParameter("id"));
            ClubService clubservice = new ClubService(HibernateUtil.getSessionFactory());

            // Fetch the club with events loaded
            Club club = clubservice.getClubWithEvents(clubId);


            if(club != null) {
                HttpSession httpSession = request.getSession();
                Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
                
                if(user != null) {
                	if(membreClubService.isMember(clubId, user.getEtudiant().getIdEtudiant())) {
                		request.setAttribute("isMember", true);
                	}else {
                		request.setAttribute("isMember", false);

                	}
                }

            	
            	
            	
            	
            	
                int memberCounts = clubservice.getMemberCount(clubId);

            	Set<Evenement> evenements = (Set<Evenement>) club.getEvenements();

                request.setAttribute("club", club);
                request.setAttribute("memberCounts", memberCounts);
                request.setAttribute("evenements", evenements);
                
                request.getRequestDispatcher("/pages/club.jsp").forward(request, response);
            } else {
            	request.getRequestDispatcher("/pages/home.jsp?message=Club not found&error=true").forward(request, response);
            }
        } catch (NumberFormatException e) {
        	request.getRequestDispatcher("/pages/home.jsp?message=Club id not found&error=true").forward(request, response);
        }
    }

}
