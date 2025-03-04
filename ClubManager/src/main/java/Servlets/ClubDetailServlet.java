package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.clubManager.service.ClubService;
import com.example.clubManager.model.Club;
/**
 * Servlet implementation class ClubDetailServlet
 */
@SuppressWarnings("serial")
@WebServlet("/club")
public class ClubDetailServlet extends HttpServlet {
    @SuppressWarnings("unused")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	
        request.setAttribute("currentPage", "club");
        try {
            int clubId = Integer.parseInt(request.getParameter("id"));
            //Club club = ClubService.getClubById(clubId);

            //club != null
            if(true) {
                //request.setAttribute("club", club);
                request.getRequestDispatcher("/pages/club.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Club non trouvé");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de club invalide");
        }
    }

}
