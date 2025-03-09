package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.example.clubManager.services.EvenementService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/admin/deleteEvent")
public class AdminDeleteEventServlet extends HttpServlet {

    private EvenementService evenementService;

    @Override
    public void init() throws ServletException {
        super.init();
        evenementService = new EvenementService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int eventId = Integer.parseInt(request.getParameter("eventId"));

        try {
            // Delete the event
            evenementService.deleteEvenement(eventId);
            response.sendRedirect(request.getContextPath() + "/admin/events?message=Event deleted successfully&success=true");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/events?message=Error while deleting&error=true");
            e.printStackTrace();
        }
        
    }
}