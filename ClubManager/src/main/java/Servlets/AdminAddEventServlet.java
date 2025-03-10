package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import com.example.clubManager.models.Club;
import com.example.clubManager.models.Evenement;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.ClubService;
import com.example.clubManager.services.EvenementService;
import com.example.clubManager.util.HibernateUtil;
import java.text.ParseException;






@SuppressWarnings("serial")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 5,   // 5MB
	    maxRequestSize = 1024 * 1024 * 5 // 5MB
	)
@WebServlet(urlPatterns = {"/admin/addEvent" ,"/addEvent"} )
public class AdminAddEventServlet extends HttpServlet {
    

    private EvenementService evenementService;
    private ClubService clubService;

    @Override
    public void init() throws ServletException {
        super.init();
        clubService = new ClubService(HibernateUtil.getSessionFactory());
        evenementService = new EvenementService(HibernateUtil.getSessionFactory());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
        	
        	if(request.getParameter("clubId") != null ) {
                int clubId = Integer.parseInt(request.getParameter("clubId"));
                Club preselectedClub = clubService.getClubById(clubId);
                if(preselectedClub != null) {
                    request.setAttribute("preselectedClubId", clubId);
                }
        	}

            

            List<Club> clubs = clubService.getAllClubs();
            
            request.setAttribute("clubs", clubs);
            HttpSession session = request.getSession();
            Utilisateur user = (Utilisateur) session.getAttribute("user");


            if(user.getRole().equals("admin")) {
                request.getRequestDispatcher("/pages/admin/adminAddEvent.jsp").forward(request, response);

            }else {
                request.getRequestDispatcher("/pages/presidentAddEvent.jsp").forward(request, response);

            }
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/events?message=Invalid club ID&error=true");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/events?message=Error loading form: " + e.getMessage() + "&error=true");
        }
    }
    
    
    
    
    
    
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Evenement event = new Evenement();
            event.setNom(request.getParameter("nom"));
            event.setDescription(request.getParameter("description"));
            
            
            event.setLieu(request.getParameter("lieu"));
            
            
            
            // Retrieve the uploaded image file
            Part filePart = request.getPart("image");
            byte[] imageBytes = null;
            if(filePart != null && filePart.getSize() > 0) {
            	try (InputStream inputStream = filePart.getInputStream()) {
            		imageBytes = inputStream.readAllBytes();
            	}

            }
            
            event.setImage(imageBytes);
            
            // Parse date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            event.setDateEvenement(dateFormat.parse(request.getParameter("date")));
            
            // Set club association
            int clubId = Integer.parseInt(request.getParameter("clubId"));
            event.setClub(clubService.getClubById(clubId));
            
            // Save event
            evenementService.saveEvenement(event);
            
            
            HttpSession session = request.getSession();
            Utilisateur user = (Utilisateur) session.getAttribute("user");


            if(user.getRole().equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin/events?message=Event created successfully&success=true");

            }else {
            	response.sendRedirect(request.getContextPath() + "/my_clubs?message=Event created successfully&success=true");
                

            }
            
            
            
        } catch (ParseException e) {
            response.sendRedirect(request.getContextPath() + "/admin/addEvent?clubId=" + request.getParameter("clubId") + 
                "&message=Invalid date format&error=true");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/events?message=Invalid club ID&error=true");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/events?message=Error creating event: " + e.getMessage() + "&error=true");
        }
    }
    
    
    
    
}