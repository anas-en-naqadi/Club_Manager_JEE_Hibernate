package Servlets;

import com.example.clubManager.models.Club;
import com.example.clubManager.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import java.io.IOException;

@WebServlet("/clubImage")
public class ClubImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("id"));
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Club club = session.get(Club.class, clubId);
            if (club != null && club.getImage() != null) {
                response.setContentType("image/jpeg");
                response.getOutputStream().write(club.getImage());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
