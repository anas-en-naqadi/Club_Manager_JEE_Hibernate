package Servlets;

import com.example.clubManager.models.Club;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/admin/clubs")
public class AdminClubs extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        ClubService clubService = new ClubService(HibernateUtil.getSessionFactory());
        
        try {
            // Retrieve all clubs using the service layer
            List<Club> clubs = clubService.getAllClubs();
            
            Map<Integer, Integer> memberCounts = new HashMap<>();
            
            for (Club club : clubs) {
                int count = clubService.getMemberCount(club.getIdClub());
                memberCounts.put(club.getIdClub(), count);
            }
            
            request.setAttribute("clubs", clubs);
            request.setAttribute("memberCounts", memberCounts);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("clubs", null);
            request.setAttribute("memberCounts", null);
        }
        
        // Forward to the JSP page
        request.getRequestDispatcher("/pages/admin/adminClubs.jsp").forward(request, response);
    }


}