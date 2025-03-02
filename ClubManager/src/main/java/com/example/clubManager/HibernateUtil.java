package com.example.clubManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void main(String[] args) {
        // Test Hibernate connection
        try (Session session = getSessionFactory().openSession()) {
            System.out.println("✅ Hibernate is connected to the database successfully!");
        } catch (Exception e) {
            System.err.println("❌ Hibernate failed to connect: " + e.getMessage());
        }
    }
}

