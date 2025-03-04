package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.clubManager.service.ClubService;

/**
 * Servlet implementation class ClubListServlet
 */
@SuppressWarnings("serial")
@WebServlet("/clubes")
public class ClubListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	
        request.setAttribute("currentPage", "clubes");
    	//get all clubs
        //request.setAttribute("clubs", ClubService.getAllClubs());
        request.getRequestDispatcher("/pages/clubes.jsp").forward(request, response);
    }
}
