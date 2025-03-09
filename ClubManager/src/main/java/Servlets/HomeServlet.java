package Servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.clubManager.models.Club;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.util.HibernateUtil;



/**
 * Servlet implementation class HomeServlet
 */



@SuppressWarnings("serial")
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	
        ClubService clubService = new ClubService(HibernateUtil.getSessionFactory());

    	try {
    	    List<Club> popularClubs = ClubService.getPopularClubsByMembers(5);
    	    
    	    
    	    
    	    
            Map<Integer, Integer> memberCounts = new HashMap<>();
            
            for (Club club : popularClubs) {
                int count = clubService.getMemberCount(club.getIdClub());
                memberCounts.put(club.getIdClub(), count);
            }
            
            
    	    request.setAttribute("popularClubs", popularClubs);
            request.setAttribute("memberCounts", memberCounts);

    	} catch (Exception e) {
    	    request.setAttribute("errorMessage", "Could not load popular clubs: " + e.getMessage());
    	    e.printStackTrace();
    	}
        
        request.setAttribute("currentPage", "home");
        request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
    }
    
    
    
}



