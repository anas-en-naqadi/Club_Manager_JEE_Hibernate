package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyClubs
 */
@SuppressWarnings("serial")
@WebServlet("/my_clubs")
public class MyClubsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	
    	
        request.setAttribute("currentPage", "myClubs");
        //HttpSession session = (HttpSession) request.getSession(false);
        //if (session == null || session.getAttribute("user") == null) {
            //response.sendRedirect(request.getContextPath() + "/pages/login");
            //return;
        //}

        
        // etd clubs
        //User user = (User) session.getAttribute("user");
        //<Integer> clubIds = UserService.getUserClubs(user.getId());
        // <Club> clubs = clubIds.stream().map(ClubService::getClubById).collect(Collectors.toList());

        //request.setAttribute("clubs", clubs);

        request.getRequestDispatcher("/pages/my-clubs.jsp").forward(request, response);
    }
    
    
    
    
}




