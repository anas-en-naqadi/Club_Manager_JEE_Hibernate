package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.example.clubManager.models.Evenement;
import com.example.clubManager.services.EvenementService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/admin/events")
public class AdminEvenementServlet extends HttpServlet {
    
    private EvenementService evenementService;

    @Override
    public void init() throws ServletException {
        super.init();
        evenementService = new EvenementService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Evenement> events = evenementService.getAllEvenementsWithDetails();
        request.setAttribute("events", events);
        request.getRequestDispatcher("/pages/admin/adminEvents.jsp").forward(request, response);
    }
    
    
    
    
}