
import com.example.clubManager.util.HibernateUtil;

public class HibernateTest {
    public static void main(String[] args) {
        try {
            System.out.println("Initializing Hibernate...");
            HibernateUtil.getSessionFactory();
            System.out.println("Hibernate initialized successfully!");
            HibernateUtil.shutdown();
        } catch (Exception e) {
            System.err.println("Hibernate initialization failed:");
            e.printStackTrace();
        }
    }
}