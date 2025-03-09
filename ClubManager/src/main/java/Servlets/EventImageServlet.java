package Servlets;

import com.example.clubManager.models.Evenement;
import com.example.clubManager.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/eventImage")
public class EventImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        int eventId = Integer.parseInt(request.getParameter("id"));
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Evenement evenement = session.get(Evenement.class, eventId);
            if (evenement != null && evenement.getImage() != null) {
                response.setContentType("image/jpeg");
                response.getOutputStream().write(evenement.getImage());
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
