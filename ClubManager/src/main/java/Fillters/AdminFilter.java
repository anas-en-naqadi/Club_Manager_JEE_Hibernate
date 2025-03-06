package Fillters;


import java.io.IOException;

import com.example.clubManager.models.Utilisateur;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
        // Cast to HTTP-specific request and response objects
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the session, if it exists (false prevents creating a new session)
        HttpSession session = httpRequest.getSession(false);

        // Check if the user is logged in
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAdmin = false;

        // If logged in, verify if the user has the admin role
        if (isLoggedIn) {
        	Utilisateur user = (Utilisateur) session.getAttribute("user");
            isAdmin = user.getRole().equals("admin");
        }

        // Allow access if authenticated and admin, otherwise redirect
        if (!isLoggedIn) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?error=Please+login+first"); // Redirect to login page

        } else if(!isAdmin) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/401"); // Redirect to login page
        } else {
        	chain.doFilter(request, response); 
        }
    }


}