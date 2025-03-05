package Servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Servlet implementation class HomeServlet
 */



@SuppressWarnings("serial")
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        request.setAttribute("currentPage", "home");
    	//List of top clubs by number of members 
        //request.setAttribute("TopClubs", ClubService.getTopClubs());
        request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
    }
}



