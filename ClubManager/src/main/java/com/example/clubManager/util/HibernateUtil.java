package com.example.clubManager.util;

import com.example.clubManager.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // Load hibernate.cfg.xml from classpath
            .build();
        
        try {
            return new MetadataSources(registry)
                .addAnnotatedClass(Utilisateur.class)
                .addAnnotatedClass(Etudiant.class)
                .addAnnotatedClass(Club.class)
                .addAnnotatedClass(Evenement.class)
                .addAnnotatedClass(MembreClub.class)
                .getMetadataBuilder()
                .build()
                .getSessionFactoryBuilder()
                .build();
        } catch (Exception e) {

            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}