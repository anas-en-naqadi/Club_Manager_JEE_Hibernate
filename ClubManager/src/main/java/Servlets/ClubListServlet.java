package Servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


import com.example.clubManager.models.Club;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.util.HibernateUtil;



/**
 * Servlet implementation class ClubListServlet
 */
@SuppressWarnings("serial")
@WebServlet("/clubes")
public class ClubListServlet extends HttpServlet {
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
        
        request.setAttribute("currentPage", "clubes");
        
        request.getRequestDispatcher("/pages/clubes.jsp").forward(request, response);
    }
}
