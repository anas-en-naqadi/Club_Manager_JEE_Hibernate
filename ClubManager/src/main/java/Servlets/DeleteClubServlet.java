package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.clubManager.services.ClubService;
import com.example.clubManager.util.HibernateUtil;

/**
 * Servlet implementation class DeleteClubServlet
 */
@SuppressWarnings("serial")
@WebServlet("/admin/deleteClub")
public class DeleteClubServlet extends HttpServlet {
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Get the club ID from the request parameter
	        int id = Integer.parseInt(request.getParameter("id"));

	        // Delete the club
	        ClubService clubService = new ClubService(HibernateUtil.getSessionFactory());
	        clubService.deleteClub(id);

	        // Redirect to adminClubs page with success message
	        response.sendRedirect(request.getContextPath() + "/pages/admin/adminClubs.jsp?message=Club deleted");
	    } catch (Exception e) {
	        // Redirect to adminClubs page with an error message if something goes wrong
	        response.sendRedirect(request.getContextPath() + "/pages/admin/adminClubs.jsp?error=Failed to delete club");
	    }
	}


}
