package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.example.clubManager.models.Etudiant;
import com.example.clubManager.services.EtudiantService;
import com.example.clubManager.util.HibernateUtil;

@SuppressWarnings("serial")
@WebServlet("/admin/etudiants")
public class AdminStudentsServlet extends HttpServlet {
    
    private EtudiantService etudiantService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        etudiantService = new EtudiantService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession();
        try {
            List<Etudiant> students = etudiantService.getAllEtudiantsWithClubs();
            request.setAttribute("students", students);
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Error loading students: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/pages/admin/adminEtudiants.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        try {
        	etudiantService.deleteEtudiant(studentId);
        	
            response.sendRedirect(request.getContextPath() + "/admin/etudiants?message=Student successfully deleted&success=true");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/etudiants?message=Error student not deleted&error=true");
            System.out.println(e.getMessage());
        }
        
        
    }


    
    
}