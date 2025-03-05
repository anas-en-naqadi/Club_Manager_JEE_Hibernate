 package com.example.clubManager.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Charge la configuration depuis hibernate.cfg.xml et construit la SessionFactory
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Échec de l'initialisation de la SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Méthode pour obtenir la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Méthode pour fermer la SessionFactory (à appeler lors de l'arrêt de l'application)
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}