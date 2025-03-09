package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.clubManager.models.Club;
import com.example.clubManager.models.Evenement;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.services.EvenementService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/admin/editEvent")
public class AdminEditEventServlet extends HttpServlet {
    
    private EvenementService evenementService;
    private ClubService clubService;

    @Override
    public void init() throws ServletException {
        super.init();
        evenementService = new EvenementService(HibernateUtil.getSessionFactory());
        clubService = new ClubService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int eventId = Integer.parseInt(request.getParameter("id"));
        
        try {
            Evenement event = evenementService.getEvenementById(eventId);
            List<Club> clubs = clubService.getAllClubs();
            
            request.setAttribute("event", event);
            request.setAttribute("clubs", clubs);
            request.getRequestDispatcher("/pages/admin/editEvent.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.sendRedirect("evenements?message=Error retrieving event&error=true");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int eventId = Integer.parseInt(request.getParameter("id"));
        
        try {
            Evenement event = evenementService.getEvenementById(eventId);
            
            // Update event properties
            event.setNom(request.getParameter("nom"));
            event.setDescription(request.getParameter("description"));
            event.setLieu(request.getParameter("lieu"));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            event.setDateEvenement(dateFormat.parse(request.getParameter("date")));
            
            // Update club association
            int clubId = Integer.parseInt(request.getParameter("clubId"));
            event.setClub(clubService.getClubById(clubId));
            
            evenementService.updateEvenement(event);
            
            response.sendRedirect("evenements?message=Événement mis à jour avec succès&success=true");
            
        } catch (ParseException e) {
            response.sendRedirect("editEvent?id=" + eventId + "&message=Format de date invalide&error=true");
        } catch (Exception e) {
            response.sendRedirect("editEvent?id=" + eventId + "&message=Erreur de mise à jour&error=true");
        }
    }
}