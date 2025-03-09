package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.clubManager.models.Club;
import com.example.clubManager.models.MembreClub;
import com.example.clubManager.models.Utilisateur;
import com.example.clubManager.services.MembreClubService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/my_clubs")
public class MyClubsServlet extends HttpServlet {
    
    private MembreClubService membreClubService;

    @Override
    public void init() throws ServletException {
        super.init();
        membreClubService = new MembreClubService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        request.setAttribute("currentPage", "myClubs");

        Utilisateur user = (Utilisateur) session.getAttribute("user");

        // Get clubs through MembreClub relationships
        List<MembreClub> memberships = membreClubService.getMembreClubsByMembre(user.getEtudiant().getIdEtudiant());
        
        // Extract clubs from memberships
        List<Club> clubs = memberships.stream()
                .map(MembreClub::getClub)
                .collect(Collectors.toList());

        request.setAttribute("clubs", clubs);
        request.getRequestDispatcher("/pages/my-clubs.jsp").forward(request, response);
    }
}